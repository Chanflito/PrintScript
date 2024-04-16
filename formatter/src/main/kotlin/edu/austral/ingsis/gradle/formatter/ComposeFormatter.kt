package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode

class ComposeFormatter(private val formatters: List<Formatter<AST>>) : Formatter<AST> {
    override fun format(node: AST): String {
        if (node is ProgramNode) {
            val nodes = node.children
            return nodes.joinToString("\n") { format(it) }
        }
        val formatter = formatters.firstOrNull { it.canFormat(node) }
        return formatter?.format(node) ?: "Unsupported node type"
    }

    override fun canFormat(node: AST): Boolean {
        return formatters.any { it.canFormat(node) }
    }
}
