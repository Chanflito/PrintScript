package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ReAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: List<Rule>,
    ): String {
        return when (node) {
            is ReAssignationNode -> {
                val identifier = node.identifierNode.name
                val expression = ExpressionFormatter().format(node.expression, rules)
                val result = "$identifier = $expression;"
                return applyFormat(result, rules)
            }
            else -> ""
        }
    }

    override fun applyFormat(
        result: String,
        rules: List<Rule>,
    ): String {
        val composedRule = ComposeRule(rules)

        return composedRule.applyRule(result)
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReAssignationNode
    }
}
