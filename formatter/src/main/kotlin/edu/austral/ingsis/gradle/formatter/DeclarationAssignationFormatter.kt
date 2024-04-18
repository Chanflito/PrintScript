package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.formatter.rule.Rule
import java.util.Locale.getDefault

class DeclarationAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is DeclarationAssignation -> {
                val keyword = node.keyword.value
                val identifier = node.identifierNode.name
                val nodeType = node.nodeType.toString().lowercase(getDefault())
                val expression = ExpressionFormatter().format(node.expression, defaultRule, ifBlockRule)
                val result = "$keyword $identifier:$nodeType=$expression;"
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
        return node is DeclarationAssignation
    }
}
