package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface AST {
    val tokenPosition: TokenPosition
}
