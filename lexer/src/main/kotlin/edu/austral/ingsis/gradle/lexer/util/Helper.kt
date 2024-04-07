package edu.austral.ingsis.gradle.lexer.util

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.LeftBrace
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.Minus
import edu.austral.ingsis.gradle.common.token.Multiply
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.RightBrace
import edu.austral.ingsis.gradle.common.token.RightParenthesis
import edu.austral.ingsis.gradle.common.token.SemiColon
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenPosition
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.util.calculatePosition

fun createToken(
    matchResult: MatchResult,
    code: String,
    tokenType: TokenType,
): Token {
    val startIndex = matchResult.range.first
    val endIndex = matchResult.range.last + 1
    val startPosition = calculatePosition(code, startIndex)
    val endPosition = calculatePosition(code, endIndex)
    return Token(matchResult.value, tokenType, TokenPosition(startPosition, endPosition))
}

fun isInQuotes(
    matchResult: MatchResult,
    code: String,
): Boolean { // How this should be more efficient?
    val quotesMatch =
        RegexPatterns.QUOTES_REGEX.findAll(code).map { result -> Pair(result.range.first, result.range.last + 1) }
            .toList()
    val resultRange = Pair(matchResult.range.first, matchResult.range.last + 1)
    return quotesMatch.any { (start, end) -> start <= resultRange.first && end >= resultRange.second }
}

val operators =
    mapOf(
        "+" to Plus,
        "-" to Minus,
        "*" to Multiply,
        "/" to Divide,
        "=" to Assignation,
        "(" to LeftParenthesis,
        ")" to RightParenthesis,
        ":" to Colon,
        ";" to SemiColon,
    )

val bracketsOperators =
    mapOf(
        "{" to LeftBrace,
        "}" to RightBrace,
    )
