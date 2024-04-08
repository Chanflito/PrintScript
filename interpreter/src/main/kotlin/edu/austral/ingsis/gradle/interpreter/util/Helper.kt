package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanType
import edu.austral.ingsis.gradle.common.ast.newast.NumberType
import edu.austral.ingsis.gradle.common.ast.newast.StringType
import edu.austral.ingsis.gradle.common.ast.newast.Type

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

fun doesTypeMatch(
    result: Any?,
    type: Type?,
): Boolean {
    return when (type) {
        is StringType -> result is String
        is NumberType -> result is Number
        is BooleanType -> result is Boolean
        else -> false
    }
}
