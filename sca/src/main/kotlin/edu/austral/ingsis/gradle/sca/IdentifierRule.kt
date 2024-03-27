package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.sca.util.generateReport

class IdentifierRule (private val regex: Regex): Rule<ASTNode>{
    override fun verify(node: ASTNode): ReportResult {
        return when (node.nodeType){
            is ProgramNode -> verifyChildren(node.children)
            else -> ReportSuccess
        }
    }

    private fun verifyChildren(nodes: List<ASTNode>): ReportResult{
        if (nodes.isEmpty()) return ReportSuccess
        val reports= nodes.map { node -> when (node.nodeType){
            is IdentifierNode -> applyRegex(node.value.toString())
            is AssignationNode -> verifyChildren(node.children)
            else -> ReportSuccess
        } }
        return generateReport(reports)
    }

    private fun applyRegex(value: String) : ReportResult{
        return if (value.matches(regex)) ReportSuccess else ReportError("Invalid identifier")
    }
}