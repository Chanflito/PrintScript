package interpreter.util

import common.ast.ASTNode
import common.ast.*

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