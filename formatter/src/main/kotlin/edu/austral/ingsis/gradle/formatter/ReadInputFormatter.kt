package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode

class ReadInputFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is ReadInputNode -> {
                val value = ExpressionFormatter().format(node.expression)
                "readInput($value)"
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReadInputNode
    }
}
