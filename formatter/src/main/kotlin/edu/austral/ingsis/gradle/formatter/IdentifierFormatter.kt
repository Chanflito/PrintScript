package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode

class IdentifierFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is IdentifierNode -> {
                return node.name
            }

            else -> ""
        }
    }
}
