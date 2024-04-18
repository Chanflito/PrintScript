package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface Keyword : AST {
    val value: String
}

data class LetKeywordNode(override val tokenPosition: TokenPosition) : Keyword {
    override val value: String = "let"
}

data class ConstKeywordNode(override val tokenPosition: TokenPosition) : Keyword {
    override val value: String = "const"
}
