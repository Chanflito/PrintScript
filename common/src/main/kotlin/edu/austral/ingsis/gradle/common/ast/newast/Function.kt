package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface Function : Statement

data class PrintLnNode(override val tokenPosition: TokenPosition, val expression: Expression) : Function

data class ReadInputNode(override val tokenPosition: TokenPosition, val value: String) : Function, Expression

data class ReadEnvNode(override val tokenPosition: TokenPosition, val value: String) : Function, Expression
