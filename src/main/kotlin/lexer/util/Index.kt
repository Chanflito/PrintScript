package lexer.util

import common.token.Token
import common.token.TokenType
import common.token.calculatePosition

fun createToken(matchResult: MatchResult, code: String, tokenType:TokenType): Token {
    val value = matchResult.value;
    val startIndex = matchResult.range.first;
    val endIndex = matchResult.range.last + 1;
    val startPosition = calculatePosition(code, startIndex)
    val endPosition = calculatePosition(code, endIndex)
    return Token(value, tokenType, startPosition, endPosition)
}