package edu.austral.ingsis.gradle.sca.rule

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Declaration
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.token.TokenPosition
import edu.austral.ingsis.gradle.sca.ReportErrorMessage
import edu.austral.ingsis.gradle.sca.ReportFailure
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.IdentifierRuleType

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
            else -> ReportSuccess
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
