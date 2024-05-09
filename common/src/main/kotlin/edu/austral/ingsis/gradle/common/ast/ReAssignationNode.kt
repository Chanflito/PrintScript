package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

data class ReAssignationNode(
    override val tokenPosition: TokenPosition,
    override val expression: Expression,
    val identifierNode: IdentifierNode,
) : Assignation
