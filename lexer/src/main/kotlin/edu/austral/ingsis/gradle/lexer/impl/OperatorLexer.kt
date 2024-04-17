package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

class OperatorLexer(private val tokens: Map<String, TokenType>) : Lexer {
    private val regex = RegexPatterns.OPERATOR_REGEX(tokens)

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
