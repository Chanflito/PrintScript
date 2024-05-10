package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignationNode
import edu.austral.ingsis.gradle.formatter.rule.Rules
import java.util.Locale.getDefault

class DeclarationAssignationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is DeclarationAssignationNode) throw IllegalArgumentException("$node is not a DeclarationAssignation")

        val keyword = node.keyword.value
        val identifier = node.identifierNode.name
        val nodeType = node.nodeType.toString().lowercase(getDefault())
        val composeFormatter = createDefaultFormatter()
        val expression = composeFormatter.format(node.expression, rules)
        val result = "$keyword $identifier:$nodeType=$expression;"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is DeclarationAssignationNode
    }
}
