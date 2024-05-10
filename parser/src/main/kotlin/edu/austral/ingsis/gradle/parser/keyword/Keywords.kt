package edu.austral.ingsis.gradle.parser.keyword

import edu.austral.ingsis.gradle.common.ast.ConstKeywordNode
import edu.austral.ingsis.gradle.common.ast.Keyword
import edu.austral.ingsis.gradle.common.ast.LetKeywordNode
import edu.austral.ingsis.gradle.common.token.Token

val KEYWORDS_MAP =
    mapOf(
        "let" to LetKeywordKeywordBuilder(),
        "const" to ConstKeywordKeywordBuilder(),
    )

interface KeywordBuilder {
    fun execute(
        token: Token,
        index: Int,
    ): Pair<Keyword, Int>
}

class LetKeywordKeywordBuilder : KeywordBuilder {
    override fun execute(
        token: Token,
        index: Int,
    ): Pair<Keyword, Int> {
        return Pair(LetKeywordNode(token.tokenPosition), index)
    }
}

class ConstKeywordKeywordBuilder : KeywordBuilder {
    override fun execute(
        token: Token,
        index: Int,
    ): Pair<Keyword, Int> {
        return Pair(ConstKeywordNode(token.tokenPosition), index)
    }
}
