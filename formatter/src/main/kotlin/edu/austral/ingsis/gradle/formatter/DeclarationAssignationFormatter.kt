package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode

class DeclarationAssignationFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is ProgramNode -> formatChildren(node.children)
            is DeclarationAssignation -> formatDeclarationAssignation(node)
            else -> ""
        }
    }

    private fun formatChildren(nodes: List<AST>): String {
        val formattedDeclarations =
            nodes.map { child ->
                when (child) {
                    is DeclarationAssignation -> formatDeclarationAssignation(child)
                    else -> ""
                }
            }
        return formattedDeclarations.joinToString("\n")
    }

    private fun formatDeclarationAssignation(node: DeclarationAssignation): String {
        val keyword = node.keyword.value
        val identifier = node.identifierNode.name
        val nodeType = node.nodeType.toString()
        val expression = ExpressionFormatter().format(node.expression)
        return "$keyword $identifier : $nodeType = $expression"
    }
}
