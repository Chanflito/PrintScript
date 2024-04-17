package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IfStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rule: Rule,
    ): String {
        return when (node) {
            is IfStatement -> {
                val condition = ExpressionFormatter().format(node.condition, rule)
                val block = formatIfBlock(node.ifBlock.children, rule)
                val result = "if ($condition) {\n${block}\n}"
                return applyFormat(result, rule)
            }

            else -> ""
        }
    }

    override fun applyFormat(
        result: String,
        rule: Rule,
    ): String {
        return rule.applyRule(result)
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfStatement
    }

    private fun formatIfBlock(
        nodes: List<AST>,
        ruleData: Rule,
    ): String {
        val blockFormatter = createDefaultFormatter()
        return nodes.joinToString("\n") { blockFormatter.format(it, ruleData) }
    }
}