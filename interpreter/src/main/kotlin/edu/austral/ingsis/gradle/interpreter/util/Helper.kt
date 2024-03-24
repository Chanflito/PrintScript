package edu.austral.ingsis.gradle.interpreter.util

import common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.ast.TypeNode

fun isType(node: ASTNode): Boolean{
    return node.nodeType is TypeNode
}

fun isIdentifier(node: ASTNode): Boolean{
    return node.nodeType is IdentifierNode
}

fun isValueString(node: ASTNode?): Boolean{
    return node?.nodeType is StringNode
}

fun isKeyword(node: ASTNode): Boolean{
    return node.nodeType is KeywordNode
}