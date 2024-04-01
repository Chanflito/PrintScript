package edu.austral.ingsis.gradle.formatter.util

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.NodeType
import edu.austral.ingsis.gradle.common.ast.NumberNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.StringNode

/**
 * Finds the right side of an AssignationNode.
 * This method retrieves everything after the equals sign.
 * Examples of the right side include:
 * - 5
 * - 5/5
 * - a
 * @return The right side of the assignment node.
 */
fun findIdentifierOrNumberOrStringOrOperatorNode(nodes: List<ASTNode>): String {
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

fun findNode(
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
