package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IdentifierFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rule: Rule,
    ): String {
        return when (node) {
            is IdentifierNode -> {
                return applyFormat(node.name, rule)
            }

            else -> ""
        }
    }

    override fun applyFormat(
        result: String,
        rule: Rule,
    ): String {
        return rule.applyRule(result)
    }

    override fun canFormat(node: AST): Boolean {
        return node is IdentifierNode
    }
}
