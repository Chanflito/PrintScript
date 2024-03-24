package common.ast

import edu.austral.ingsis.gradle.common.ast.NodeType
import common.token.Token

data class ASTNodeImpl(
    override val value: Any?,
    override val token: Token?,
    override val nodeType: NodeType,
    override val children: List<ASTNode>
) : ASTNode{
    fun addChild(child: ASTNode): ASTNodeImpl {
        val updatedChildren = children.orEmpty().plus(child)
        return this.copy(children = updatedChildren)
    }
}
