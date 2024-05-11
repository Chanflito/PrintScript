package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.formatter.rule.Rules
import java.util.Locale.getDefault

class DeclarationFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is DeclarationNode) throw IllegalArgumentException("$node is not a Declaration")

        val keyword = node.keyword.value
        val identifier = node.identifierNode.name
        val nodeType = node.nodeType.toString().lowercase(getDefault())
        val result = "$keyword $identifier:$nodeType;"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is DeclarationNode
    }
}
