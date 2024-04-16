package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode

class ReAssignationFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is ProgramNode -> formatChildren(node.children)
            is ReAssignationNode -> formatReAssignation(node)
            else -> ""
        }
    }

    private fun formatChildren(nodes: List<AST>): String {
        val formattedDeclarations =
            nodes.map { child ->
                when (child) {
                    is ReAssignationNode -> formatReAssignation(child)
                    else -> ""
                }
            }
        return formattedDeclarations.joinToString("\n")
    }

    private fun formatReAssignation(node: ReAssignationNode): String {
        val identifier = node.identifierNode.name
        val expression = ExpressionFormatter().format(node.expression)
        return "$identifier = $expression"
    }
}
