package common.ast
import common.token.Token

interface ASTNode {
    val token: Token?
    val children: List<ASTNode>?
    val nodeType: NodeType
}