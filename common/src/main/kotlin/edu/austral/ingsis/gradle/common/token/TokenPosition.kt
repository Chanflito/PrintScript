package edu.austral.ingsis.gradle.common.token

data class TokenPosition(
    val startPosition: Position,
    val endPosition: Position,
)

fun defaultTokenPosition(): TokenPosition {
    return TokenPosition(Position(0, 0), Position(0, 0))
}
