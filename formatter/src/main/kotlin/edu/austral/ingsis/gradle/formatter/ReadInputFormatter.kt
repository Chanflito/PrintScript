package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ReadInputFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: List<Rule>,
    ): String {
        return when (node) {
            is ReadInputNode -> {
                val value = ExpressionFormatter().format(node.expression, rules)
                val result = "readInput($value);"
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
        return node is ReadInputNode
    }
}
