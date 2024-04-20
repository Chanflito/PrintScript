package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ComposeFormatter(private val formatters: List<Formatter<AST>>) : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        val formatter = formatters.firstOrNull { it.canFormat(node) }
        return formatter?.format(node, defaultRule, blockRule) ?: "Unsupported node type"
    }

    override fun canFormat(node: AST): Boolean {
        return formatters.any { it.canFormat(node) }
    }
}
