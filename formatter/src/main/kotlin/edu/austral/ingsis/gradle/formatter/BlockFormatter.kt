package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class BlockFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        return when (node) {
            is BlockNode -> {
                val composeFormatter = createDefaultFormatter()
                // ComposeFormatter case where each child is formatted
                val formatted =
                    node.children.joinToString("\n") {
                        composeFormatter.format(it, rules)
                    }
                val lines = formatted.split("\n")
                return lines.joinToString("\n") { applyFormat(it, rules.blockRule) }
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is BlockNode
    }
}
