package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.Minus
import edu.austral.ingsis.gradle.common.token.Multiply
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.RightParenthesis
import edu.austral.ingsis.gradle.common.token.SemiColon
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

class OperatorLexer : Lexer {
    private val regex = RegexPatterns.OPERATOR_REGEX

    private val tokens: Map<String, TokenType> =
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

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { matchResult ->
            if (tokens.containsKey(matchResult.value) && !isInQuotes(matchResult, code)) {
                createToken(matchResult, code, tokens.getValue(matchResult.value))
            } else {
                null
            }
        }.toList()
    }
}
