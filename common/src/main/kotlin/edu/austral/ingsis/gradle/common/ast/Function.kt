package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface Function : AST

data class PrintLnNode(
    override val tokenPosition: TokenPosition,
    val expression: Expression,
) : Function

data class ReadInputNode(
    override val tokenPosition: TokenPosition,
    val expression: Expression,
) : Function, Expression

data class ReadEnvNode(
    override val tokenPosition: TokenPosition,
    val value: String,
) : Function, Expression
