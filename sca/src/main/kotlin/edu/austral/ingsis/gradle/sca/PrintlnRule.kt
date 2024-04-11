package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.Operand
import edu.austral.ingsis.gradle.common.ast.newast.Operator
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.Statement
import edu.austral.ingsis.gradle.sca.util.generateReport

class PrintlnRule : Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return when (node) {
            is ProgramNode -> verifyChildren(node.children)
            else -> ReportSuccess
        }
    }

    private fun verifyChildren(nodes: List<Statement>): ReportResult {
        if (nodes.isEmpty()) return ReportSuccess
        val reports =
            nodes.map { node ->
                when (node) {
                    is PrintLnNode -> verifyExpression(node.expression)
                    else -> ReportSuccess
                }
            }
        return generateReport(reports)
    }

    private fun verifyExpression(expression: Expression): ReportResult {
        return when (expression) {
            is Operator ->
                ReportFailure(PrintlnReportErrorMessage().build(expression.tokenPosition))
            is Operand, is ReadEnvNode, is ReadInputNode -> ReportSuccess
        }
    }
}
