package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface Type : AST

data class BooleanType(override val tokenPosition: TokenPosition) : Type

data class StringType(override val tokenPosition: TokenPosition) : Type

data class NumberType(override val tokenPosition: TokenPosition) : Type
