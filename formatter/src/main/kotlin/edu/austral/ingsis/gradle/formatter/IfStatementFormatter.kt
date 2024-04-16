package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement

class IfStatementFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is IfStatement -> {
                val condition = ExpressionFormatter().format(node.condition)
                val block = formatIfBlock(node.ifBlock.children)
                return "if ($condition) {\n${block}\n}"
            }

            else -> ""
        }
    }

    private fun formatIfBlock(nodes: List<AST>): String {
        val blockFormatter = createDefaultFormatter()
        return nodes.joinToString("\n") { blockFormatter.format(it) }
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfStatement
    }
}
