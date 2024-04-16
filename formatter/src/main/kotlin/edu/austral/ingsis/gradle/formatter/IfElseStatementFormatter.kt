package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement

class IfElseStatementFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is IfElseStatement -> {
                val condition = ExpressionFormatter().format(node.condition)
                val ifBlock = formatIfBlock(node.ifBlock.children)
                val elseBlock = formatIfBlock(node.elseBlock.children)
                return "if ($condition) {\n${ifBlock}\n}else {\n${elseBlock}\n}"
            }

            else -> ""
        }
    }

    private fun formatIfBlock(nodes: List<AST>): String {
        val blockFormatter = createDefaultFormatter()
        return nodes.joinToString("\n") { blockFormatter.format(it) }
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfElseStatement
    }
}
