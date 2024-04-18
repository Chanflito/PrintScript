package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ComposeFormatter(private val formatters: List<Formatter<AST>>) : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        when (node) {
            is ProgramNode -> {
                val nodes = node.children
                return nodes.joinToString("\n") { format(it, defaultRule, ifBlockRule) }
            }

            else -> {
                val formatter = formatters.firstOrNull { it.canFormat(node) }
                return formatter?.format(node, defaultRule, ifBlockRule) ?: "Unsupported node type"
            }
        }
    }

    // TODO
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
