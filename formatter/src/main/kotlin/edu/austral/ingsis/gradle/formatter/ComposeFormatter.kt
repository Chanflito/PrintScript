package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ComposeFormatter(private val formatters: List<Formatter<AST>>) : Formatter<AST> {
    override fun format(
        node: AST,
        rule: Rule,
    ): String {
        when (node) {
            is ProgramNode -> {
                val nodes = node.children
                return nodes.joinToString("\n") { format(it, rule) }
            }

            else -> {
                val formatter = formatters.firstOrNull { it.canFormat(node) }
                return formatter?.format(node, rule) ?: "Unsupported node type"
            }
        }
    }

    // TODO ????capaz con solo este aca funciona
    override fun applyFormat(
        result: String,
        rule: Rule,
    ): String {
        return rule.applyRule(result)
    }

    override fun canFormat(node: AST): Boolean {
        return formatters.any { it.canFormat(node) }
    }
}
