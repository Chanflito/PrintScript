package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode

class ReAssignationFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is ReAssignationNode -> {
                val identifier = node.identifierNode.name
                val expression = ExpressionFormatter().format(node.expression)
                return "$identifier = $expression"
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReAssignationNode
    }
}
