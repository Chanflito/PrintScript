package edu.austral.ingsis.gradle.lexer.util

import edu.austral.ingsis.gradle.common.token.*
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.util.calculatePosition
import edu.austral.ingsis.gradle.lexer.builder.LexerBuilderImp
import edu.austral.ingsis.gradle.lexer.impl.*
import edu.austral.ingsis.gradle.lexer.impl.NumberLexer

fun createToken(matchResult: MatchResult, code: String, tokenType: TokenType): Token {
    val startIndex = matchResult.range.first
    val endIndex = matchResult.range.last + 1
    val startPosition = calculatePosition(code, startIndex)
    val endPosition = calculatePosition(code, endIndex)
    return Token(matchResult.value, tokenType, startPosition, endPosition)
}

fun isInQuotes(matchResult: MatchResult, code: String): Boolean { //How this should be more efficient?
    val quotesMatch =
        RegexPatterns.QUOTES_REGEX.findAll(code).map { result -> Pair(result.range.first, result.range.last + 1) }
            .toList()
    val resultRange = Pair(matchResult.range.first, matchResult.range.last + 1)
    return quotesMatch.any { (start, end) -> start <= resultRange.first && end >= resultRange.second }
}

fun createComposeLexer(): Lexer {
    val lexerBuilderImp = LexerBuilderImp(listOf())
    return lexerBuilderImp
        .withLexer(KeywordLexer(mapOf("let" to LetKeyword, "println" to PrintlnKeyword)))
        .withLexer(TypeLexer(mapOf("string" to TypeString, "number" to TypeNumber)))
        .withLexer(IdentifierLexer(listOf("let", "println", "number", "string")))
        .withLexer(NumberLexer())
        .withLexer(OperatorLexer())
        .withLexer(StringLexer())
        .build()
}