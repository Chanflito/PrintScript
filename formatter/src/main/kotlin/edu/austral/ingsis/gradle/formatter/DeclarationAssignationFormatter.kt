package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rule

class DeclarationAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: List<Rule>,
    ): String {
        return when (node) {
            is DeclarationAssignation -> {
                val keyword = node.keyword.value
                val identifier = node.identifierNode.name
                val nodeType = node.nodeType.toString()
                val expression = ExpressionFormatter().format(node.expression, rules)
                val result = "$keyword $identifier:$nodeType=$expression;"
                return applyFormat(result, rules)
            }

            else -> ""
        }
    }

    override fun applyFormat(
        result: String,
        rules: List<Rule>,
    ): String {
        val composedRule = ComposeRule(rules)

        return composedRule.applyRule(result)
    }

    override fun canFormat(node: AST): Boolean {
        return node is DeclarationAssignation
    }
}
