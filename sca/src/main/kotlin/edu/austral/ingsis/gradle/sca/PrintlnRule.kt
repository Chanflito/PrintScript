package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.sca.util.generateReport

class PrintlnRule : Rule<ASTNode> {
    override fun verify(node: ASTNode): ReportResult {
        return when (node.nodeType) {
            is ProgramNode -> verifyChildren(node.children)
            else -> ReportSuccess
        }
    }

    private fun verifyChildren(nodes: List<ASTNode>): ReportResult {
        if (nodes.isEmpty()) return ReportSuccess
        val reports =
            nodes.map { node ->
                when (node.nodeType) {
                    is PrintLnNode -> verifyChildren(node.children)
                    is OperatorNode -> ReportFailure("Expressions is not supported")
                    else -> ReportSuccess
                }
            }
        return generateReport(reports)
    }
}
