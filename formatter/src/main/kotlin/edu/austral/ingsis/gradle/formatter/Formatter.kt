package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.NodeType
import edu.austral.ingsis.gradle.common.ast.NumberNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.ast.TypeNode

class Formatter {
    fun format(node: ASTNode): String {
        var result = ""
        node.children.forEachIndexed { index, childNode ->
            val formattedNode = formatNode(childNode)
            result += formattedNode
            if (index < node.children.size - 1) {
                result += "\n" // Add newline only if it's not the last element
            }
        }
        return result
    }

    private fun formatNode(node: ASTNode): String {
        return when (node.nodeType) {
            is AssignationNode -> {
                formatAssignationNode(node).plus(";")
            }
            is PrintLnNode -> {
                "\n".plus(formatPrintLnNode(node).plus(";"))
            }
            is OperatorNode -> {
                findIdentifierOrNumberOrStringOrOperatorNode(listOf(node)).plus(";")
            }
            is IdentifierNode -> {
                formatInitialization(node).plus(";")
            }
            else -> {
                throw Exception("Invalid node type")
            }
        }
    }

    // let
    private fun formatAssignationNode(node: ASTNode): String {
        if (findNode(node, KeywordNode) == null) {
            return formatReassignment(node)
        }
        return formatDeclaration(node)
    }

    private fun formatReassignment(node: ASTNode): String {
        val result = ""
        result.plus(findNode(node, IdentifierNode)?.value)
            .plus(" = ")
            .plus(findIdentifierOrNumberOrStringOrOperatorNode(node.children))
        return result
    }

    private fun formatDeclaration(node: ASTNode): String {
        val identifier = findNode(node, IdentifierNode)?.value
        val type = findNode(node, TypeNode)?.value
        val value = findIdentifierOrNumberOrStringOrOperatorNode(node.children)
        return "let".plus(" ").plus(identifier).plus(" : ").plus(type).plus(" = ").plus(value)
    }

    private fun formatPrintLnNode(node: ASTNode): String {
        return "println".plus(findIdentifierOrNumberOrStringOrOperatorNode(node.children))
    }

    private fun formatInitialization(node: ASTNode): String {
        return "let".plus(findNode(node, IdentifierNode)?.value)
            .plus(" : ")
            .plus(findNode(node, TypeNode)?.value)
    }

    // finders

    /**
     * Finds the right side of an AssignationNode.
     * This method retrieves everything after the equals sign.
     * Examples of the right side include:
     * - 5
     * - 5/5
     * - a
     * @param AssignationNode The assignment node to analyze.
     * @return The right side of the assignment node.
     */
    private fun findIdentifierOrNumberOrStringOrOperatorNode(nodes: List<ASTNode>): String {
        val builder = StringBuilder()
        for (node in nodes) {
            when (node.nodeType) {
                is OperatorNode -> {
                    builder.append(findIdentifierOrNumberOrStringOrOperatorNode(listOf(node.children[0])))
                    builder.append(" ") // Add space before operator
                    builder.append(node.value) // Add operation sign
                    builder.append(" ") // Add space after operator
                    builder.append(
                        findIdentifierOrNumberOrStringOrOperatorNode(listOf(node.children[1])),
                    ) // Recursively build child expression
                }

                is NumberNode -> builder.append(node.value) // Add number value
                is StringNode -> builder.append("\"${node.value}\"") // Add string value in quotes
                is IdentifierNode ->
                    if (node.children.isEmpty()) {
                        builder.append(node.value)
                    } else {
                        builder.append("")
                    } // Add identifier value, if it has no children it means it a variable, if it has children it is part of a declaration
            }
        }
        return builder.toString().trim()
    }

    private fun findNode(
        node: ASTNode,
        type: NodeType,
    ): ASTNode? {
        if (node.nodeType == type) {
            return node
        }
        for (child in node.children) {
            val foundNode = findNode(child, type)
            if (foundNode != null) {
                return foundNode
            }
        }
        return null
    }
}
