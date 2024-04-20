package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class ReAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        return when (node) {
            is ReAssignationNode -> {
                val identifier = node.identifierNode.name
                val composeFormatter = createDefaultFormatter()
                val expression = composeFormatter.format(node.expression, rules)
                val result = "$identifier = $expression;"
                return applyFormat(result, rules.defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReAssignationNode
    }
}
