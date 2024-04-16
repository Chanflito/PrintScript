package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation

class DeclarationAssignationFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is DeclarationAssignation -> {
                val keyword = node.keyword.value
                val identifier = node.identifierNode.name
                val nodeType = node.nodeType.toString()
                val expression = ExpressionFormatter().format(node.expression)
                return "$keyword $identifier : $nodeType = $expression;"
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is DeclarationAssignation
    }
}
