package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IdentifierFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is IdentifierNode -> {
                return applyFormat(node.name, defaultRule)
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is IdentifierNode
    }
}
