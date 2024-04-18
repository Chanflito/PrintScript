package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IfStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is IfStatement -> {
                val condition = ExpressionFormatter().format(node.condition, defaultRule, ifBlockRule)
                val block = formatIfBlock(node.ifBlock.children, defaultRule, ifBlockRule)
                val result = "if ($condition) {\n${block}\n}"
                return applyFormat(result, defaultRule)
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

    fun formatIfBlock(
        nodes: List<AST>,
        rule: Rule,
        ifBlockRules: Rule,
    ): String {
        val blockFormatter = createDefaultFormatter()
        val formattedBlock = nodes.joinToString("\n") { blockFormatter.format(it, rule, ifBlockRules) }
        val lines = formattedBlock.split("\n")
        return lines.joinToString("\n") { applyFormat(it, ifBlockRules) }
    }
}
