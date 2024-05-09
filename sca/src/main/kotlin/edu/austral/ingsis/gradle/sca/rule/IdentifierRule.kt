package edu.austral.ingsis.gradle.sca.rule

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Declaration
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.Function
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.Operand
import edu.austral.ingsis.gradle.common.ast.newast.Operator
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.token.TokenPosition
import edu.austral.ingsis.gradle.sca.ReportErrorMessage
import edu.austral.ingsis.gradle.sca.ReportFailure
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.IdentifierRuleType
import edu.austral.ingsis.gradle.sca.util.generateReport

class IdentifierRule(
    private val identifierRuleType: IdentifierRuleType,
    private val ruleTypeErrorMessageMap: Map<IdentifierRuleType, ReportErrorMessage>,
) :
    Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return when (node) {
            is Declaration -> applyRegex(node.identifierNode.name, node.identifierNode.tokenPosition)
            is ReAssignationNode -> applyRegex(node.identifierNode.name, node.identifierNode.tokenPosition)
            is IdentifierNode -> applyRegex(node.name, node.tokenPosition)
            is Function -> generateReport(analyzeFunctionCase(node))
            else -> ReportSuccess
        }
    }

    private fun analyzeFunctionCase(node: Function): List<ReportResult> {
        return when (node) {
            is PrintLnNode -> analyzeExpression(node.expression)
            is ReadInputNode -> analyzeExpression(node.expression)
            else -> listOf(ReportSuccess)
        }
    }

    private fun analyzeExpression(node: Expression): List<ReportResult> {
        return when (node) {
            is Operand -> listOf(verify(node))
            is Operator -> {
                val leftResults = analyzeExpression(node.left)
                val rightResults = analyzeExpression(node.right)
                val combinedResults = mutableListOf<ReportResult>()
                combinedResults.addAll(leftResults)
                combinedResults.addAll(rightResults)
                if (combinedResults.all { it == ReportSuccess }) {
                    listOf(ReportSuccess)
                } else {
                    combinedResults.filter { it != ReportSuccess }
                }
            }
            else -> listOf(ReportSuccess)
        }
    }

    private fun applyRegex(
        value: String,
        tokenPosition: TokenPosition,
    ): ReportResult {
        return if (value.matches(identifierRuleType.regex)) ReportSuccess else ReportFailure(listOf(getErrorMessage(tokenPosition)))
    }

    private fun getErrorMessage(tokenPosition: TokenPosition): String {
        return ruleTypeErrorMessageMap[identifierRuleType]?.build(tokenPosition) ?: "Invalid identifier"
    }
}
