package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface Declaration : Statement {
    val keyword: Keyword
    val identifierNode: IdentifierNode
    val type: Type
}

data class DeclarationNode(
    override val keyword: Keyword,
    override val tokenPosition: TokenPosition,
    override val type: Type,
    override val identifierNode: IdentifierNode,
) : Declaration
