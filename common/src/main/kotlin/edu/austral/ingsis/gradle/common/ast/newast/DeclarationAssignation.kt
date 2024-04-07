package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

data class DeclarationAssignation(
    override val keyword: Keyword,
    override val tokenPosition: TokenPosition,
    override val type: Type,
    override val identifierNode: IdentifierNode,
    override val expression: Expression,
) : Assignation, Declaration
