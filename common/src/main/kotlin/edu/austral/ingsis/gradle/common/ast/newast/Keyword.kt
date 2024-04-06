package edu.austral.ingsis.gradle.common.ast.newast

interface Keyword {
    val value: String
}

data class LetKeyword(override val value: String = "let") : Keyword

data class ConstKeyword(override val value: String = "const") : Keyword
