package lexer.impl

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken
import lexer.util.isInQuotes

class OperatorLexer : Lexer {
    private val regex = RegexPatterns.OPERATOR_REGEX

    private val tokens: Map<String, TokenType> = mapOf(
        "+" to TokenType.PLUS,
        "-" to TokenType.MINUS,
        "*" to TokenType.MULTIPLY,
        "/" to TokenType.DIVIDE,
        "=" to TokenType.ASSIGNATION,
        "(" to TokenType.LEFT_PARENTHESIS,
        ")" to TokenType.RIGHT_PARENTHESIS,
        ":" to TokenType.COLON,
        ";" to TokenType.SEMI_COLON
    )

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { matchResult ->
            if (tokens.containsKey(matchResult.value) && !isInQuotes(matchResult, code)) {
                createToken(matchResult, code, tokens.getValue(matchResult.value))
            } else null
        }.toList()
    }
}