package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST

class ComposeFormatter(private val formatters: List<Formatter<AST>>) : Formatter<AST> {
    override fun format(node: AST): String {
        // exclude empty strings to be joined in new lines
        return formatters
            .map { it.format(node) }
            .filter { it.isNotEmpty() }
            .joinToString("")
    }
}
