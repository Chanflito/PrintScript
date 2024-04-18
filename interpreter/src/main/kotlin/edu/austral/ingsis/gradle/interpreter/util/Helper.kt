package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType

fun isType(node: ASTNode): Boolean {
    return node.nodeType is TypeNode
}

fun isIdentifier(node: ASTNode): Boolean {
    return node.nodeType is IdentifierNode
}

fun isValueString(node: ASTNode?): Boolean {
    return node?.nodeType is StringNode
}

fun isKeyword(node: ASTNode): Boolean {
    return node.nodeType is KeywordNode
}


fun doesTypeMatch(result: Any, type: NodeType): Boolean {
    return when (type) {
        StringNodeType-> result is String
        NumberNodeType -> result is Int
        BooleanNodeType -> result is Boolean
        else -> false
    }
}