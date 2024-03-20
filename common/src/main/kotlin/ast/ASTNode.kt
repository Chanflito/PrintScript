package common.ast
import ast.NodeType
import common.token.Token

interface ASTNode {
    val value: Any?
    val token: Token?
    val children: List<ASTNode>?
    val nodeType: NodeType
}