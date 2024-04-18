package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.formatter.rule.Rule

class DeclarationAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rule: Rule,
        ifBlockRules: Rule,
    ): String {
        return when (node) {
            is DeclarationAssignation -> {
                val keyword = node.keyword.value
                val identifier = node.identifierNode.name
                val nodeType = node.nodeType.toString()
                val expression = ExpressionFormatter().format(node.expression, rule, ifBlockRules)
                val result = "$keyword $identifier:$nodeType=$expression;"
                return applyFormat(result, rule)
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
        return node is DeclarationAssignation
    }
}
