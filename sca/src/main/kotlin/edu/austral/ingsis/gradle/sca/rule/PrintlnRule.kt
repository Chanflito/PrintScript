package edu.austral.ingsis.gradle.sca.rule

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Expression
import edu.austral.ingsis.gradle.common.ast.Operand
import edu.austral.ingsis.gradle.common.ast.Operator
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.sca.PrintlnReportErrorMessage
import edu.austral.ingsis.gradle.sca.ReportFailure
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule

class PrintlnRule : Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return when (node) {
            is PrintLnNode -> verifyExpression(node.expression)
            else -> ReportSuccess
        }
    }

    private fun verifyExpression(expression: Expression): ReportResult {
        return when (expression) {
            is Operator, is ReadEnvNode, is ReadInputNode ->
                ReportFailure(listOf(PrintlnReportErrorMessage().build(expression.tokenPosition)))
            is Operand -> ReportSuccess
        }
    }
}
