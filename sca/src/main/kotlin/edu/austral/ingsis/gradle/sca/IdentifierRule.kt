package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.sca.util.IdentifierRuleType
import edu.austral.ingsis.gradle.sca.util.generateReport

class IdentifierRule(
    private val identifierRuleType: IdentifierRuleType,
    private val ruleTypeErrorMessageMap: Map<IdentifierRuleType, ReportErrorMessage>,
) :
    Rule<ASTNode> {
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
                    is IdentifierNode -> applyRegex(node.value.toString(), node.token)
                    is AssignationNode -> verifyChildren(node.children)
                    else -> ReportSuccess
                }
            }
        return generateReport(reports)
    }

    private fun applyRegex(
        value: String,
        token: Token?,
    ): ReportResult {
        return if (value.matches(identifierRuleType.regex)) ReportSuccess else ReportFailure(getErrorMessage(token))
    }

    private fun getErrorMessage(token: Token?): String {
        return ruleTypeErrorMessageMap[identifierRuleType]?.build(token) ?: "Invalid identifier"
    }
}
