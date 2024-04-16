package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IfStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: List<Rule>,
    ): String {
        return when (node) {
            is IfStatement -> {
                val condition = ExpressionFormatter().format(node.condition, rules)
                val block = formatIfBlock(node.ifBlock.children, rules)
                val result = "if ($condition) {\n${block}\n}"
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
        return node is IfStatement
    }

    private fun formatIfBlock(
        nodes: List<AST>,
        ruleData: List<Rule>,
    ): String {
        val blockFormatter = createDefaultFormatter()
        return nodes.joinToString("\n") { blockFormatter.format(it, ruleData) }
    }
}
