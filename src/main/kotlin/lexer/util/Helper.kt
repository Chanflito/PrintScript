package lexer.util

import common.token.*
import common.util.calculatePosition
import lexer.builder.LexerBuilderImp
import lexer.impl.*

fun createToken(matchResult: MatchResult, code: String, tokenType: TokenType): Token {
    val value = matchResult.value
    val startIndex = matchResult.range.first
    val endIndex = matchResult.range.last + 1
    val startPosition = calculatePosition(code, startIndex)
    val endPosition = calculatePosition(code, endIndex)
    return Token(value, tokenType, startPosition, endPosition)
}

fun isInQuotes(matchResult: MatchResult, code: String): Boolean { //How this should be more efficient?
    val quotesMatch =
        RegexPatterns.QUOTES_REGEX.findAll(code).map { result -> Pair(result.range.first, result.range.last + 1) }
            .toList();
    val resultRange = Pair(matchResult.range.first, matchResult.range.last + 1)
    return quotesMatch.any { (start, end) -> start <= resultRange.first && end >= resultRange.second }
}

fun createComposeLexer(): ComposeLexer {
    val lexerBuilderImp = LexerBuilderImp(ComposeLexer(mutableListOf()))
    lexerBuilderImp
        .withLexer(KeywordLexer(mapOf("let" to LetKeyword, "println" to PrintlnKeyword)))
        .withLexer(TypeLexer(mapOf("string" to TypeString, "number" to TypeNumber)))
        .withLexer(IdentifierLexer(listOf("let", "println", "number", "string")))
        .withLexer(NumberLexer())
        .withLexer(OperatorLexer())
        .withLexer(StringLexer())
    return lexerBuilderImp.build()
}