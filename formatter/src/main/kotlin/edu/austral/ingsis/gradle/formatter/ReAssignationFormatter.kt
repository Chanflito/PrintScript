package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ReAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is ReAssignationNode -> {
                val identifier = node.identifierNode.name
                val composeFormatter = createDefaultFormatter()
                val expression = composeFormatter.format(node.expression, defaultRule, blockRule)
                val result = "$identifier = $expression;"
                return applyFormat(result, defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReAssignationNode
    }
}
