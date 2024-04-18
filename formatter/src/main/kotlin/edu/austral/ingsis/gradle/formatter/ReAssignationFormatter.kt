package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ReAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is ReAssignationNode -> {
                val identifier = node.identifierNode.name
                val expression = ExpressionFormatter().format(node.expression, defaultRule, ifBlockRule)
                val result = "$identifier = $expression;"
                return applyFormat(result, defaultRule)
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
        return node is ReAssignationNode
    }
}
