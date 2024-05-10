package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

sealed interface Declaration : Statement {
    val keyword: Keyword
    val identifierNode: IdentifierNode
    val nodeType: NodeType
}

data class DeclarationNode(
    override val keyword: Keyword,
    override val tokenPosition: TokenPosition,
    override val nodeType: NodeType,
    override val identifierNode: IdentifierNode,
) : Declaration
