package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.formatter.rule.Rules

class ComposeFormatter(private val formatters: List<Formatter<AST>>) : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        val formatter = formatters.firstOrNull { it.canFormat(node) }
        return formatter?.format(node, rules) ?: "Unsupported node type"
    }

    override fun canFormat(node: AST): Boolean {
        return formatters.any { it.canFormat(node) }
    }
}
