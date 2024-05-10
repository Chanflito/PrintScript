package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

sealed interface AST {
    val tokenPosition: TokenPosition
}
