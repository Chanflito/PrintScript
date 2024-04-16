package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IfElseStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rule: Rule,
    ): String {
        return when (node) {
            is IfElseStatement -> {
                val condition = ExpressionFormatter().format(node.condition, rule)
                val ifBlock = formatIfBlock(node.ifBlock.children, rule)
                val elseBlock = formatIfBlock(node.elseBlock.children, rule)
                val result = "if ($condition) {\n${ifBlock}\n}else {\n${elseBlock}\n}"
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
        return node is IfElseStatement
    }

    private fun formatIfBlock(
        nodes: List<AST>,
        ruleData: Rule,
    ): String {
        val blockFormatter = createDefaultFormatter()
        return nodes.joinToString("\n") { blockFormatter.format(it, ruleData) }
    }
}
