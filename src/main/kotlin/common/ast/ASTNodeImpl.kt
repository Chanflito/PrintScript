package common.ast

import common.token.Token

data class ASTNodeImpl(
    override val value: Any?,
    override val token: Token?,
    override val nodeType: NodeType,
    override val children: List<ASTNode>?
) : ASTNode {
}