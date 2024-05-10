package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

sealed interface Operand : Expression

data class IdentifierNode(
    val name: String,
    override val tokenPosition: TokenPosition,
) : Operand
