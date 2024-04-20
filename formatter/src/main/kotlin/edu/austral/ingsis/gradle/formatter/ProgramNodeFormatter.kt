package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ProgramNodeFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is ProgramNode -> {
                val nodes = node.children
                val composeFormatter = createDefaultFormatter()
                return nodes.joinToString("\n") { composeFormatter.format(it, defaultRule, blockRule) }
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is ProgramNode
    }
}
