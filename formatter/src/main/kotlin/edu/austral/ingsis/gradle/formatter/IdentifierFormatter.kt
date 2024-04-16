package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IdentifierFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: List<Rule>,
    ): String {
        return when (node) {
            is IdentifierNode -> {
                return applyFormat(node.name, rules)
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
        return node is IdentifierNode
    }
}
