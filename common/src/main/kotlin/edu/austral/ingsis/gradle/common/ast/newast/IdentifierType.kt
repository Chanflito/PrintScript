package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface IdentifierType : AST

data class BooleanIdentifierType(override val tokenPosition: TokenPosition) :
    IdentifierType

data class StringIdentifierType(override val tokenPosition: TokenPosition) :
    IdentifierType

data class NumberIdentifierType(override val tokenPosition: TokenPosition) :
    IdentifierType
