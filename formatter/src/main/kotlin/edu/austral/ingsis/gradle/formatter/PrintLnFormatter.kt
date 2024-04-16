package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode

class PrintLnFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is PrintLnNode -> {
                val value = ExpressionFormatter().format(node.expression)
                "println($value);"
            }

            else -> throw Exception("Invalid node type")
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is PrintLnNode
    }
}
