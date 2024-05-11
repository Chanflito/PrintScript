package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class BlockFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is BlockNode) throw IllegalArgumentException("$node is not a BlockNode")

        val composeFormatter = createDefaultFormatter()
        // same as ComposeFormatter case where each child is formatted
        // in this case the format rules are applied to the entire line
        val formatted =
            node.children.joinToString("\n") {
                composeFormatter.format(it, rules)
            }
        val lines = formatted.split("\n")
        return lines.joinToString("\n") { applyFormat(it, rules.blockRule) }
    }

    override fun canFormat(node: AST): Boolean {
        return node is BlockNode
    }
}
