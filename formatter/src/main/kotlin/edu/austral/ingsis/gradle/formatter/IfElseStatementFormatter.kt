package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IfElseStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: List<Rule>,
    ): String {
        return when (node) {
            is IfElseStatement -> {
                val condition = ExpressionFormatter().format(node.condition, rules)
                val ifBlock = formatIfBlock(node.ifBlock.children, rules)
                val elseBlock = formatIfBlock(node.elseBlock.children, rules)
                val result = "if ($condition) {\n${ifBlock}\n}else {\n${elseBlock}\n}"
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
        return node is IfElseStatement
    }

    private fun formatIfBlock(
        nodes: List<AST>,
        ruleData: List<Rule>,
    ): String {
        val blockFormatter = createDefaultFormatter()
        return nodes.joinToString("\n") { blockFormatter.format(it, ruleData) }
    }
}
