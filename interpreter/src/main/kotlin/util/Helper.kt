package util

import ast.*
import common.ast.ASTNode

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