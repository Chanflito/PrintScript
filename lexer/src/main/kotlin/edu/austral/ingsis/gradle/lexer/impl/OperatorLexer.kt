package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.*
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

class OperatorLexer : Lexer {
    private val regex = RegexPatterns.OPERATOR_REGEX

    private val tokens: Map<String, TokenType> = mapOf(
        "+" to Plus,
        "-" to Minus,
        "*" to Multiply,
        "/" to Divide,
        "=" to Assignation,
        "(" to LeftParenthesis,
        ")" to RightParenthesis,
        ":" to Colon,
        ";" to SemiColon
    )

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { matchResult ->
            if (tokens.containsKey(matchResult.value) && !isInQuotes(matchResult, code)) {
                createToken(matchResult, code, tokens.getValue(matchResult.value))
            } else null
        }.toList()
    }
}