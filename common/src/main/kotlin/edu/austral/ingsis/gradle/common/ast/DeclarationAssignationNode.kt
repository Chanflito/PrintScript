package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

data class DeclarationAssignationNode(
    override val keyword: Keyword,
    override val tokenPosition: TokenPosition,
    override val nodeType: NodeType,
    override val identifierNode: IdentifierNode,
    override val expression: Expression,
) : Assignation, Declaration
