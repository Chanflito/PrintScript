package edu.austral.ingsis.gradle.common.ast
import edu.austral.ingsis.gradle.common.token.Token

interface ASTNode {
    val value: Any?
    val token: Token?
    val children: List<ASTNode>
    val nodeType: NodeType
}
