package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.BooleanValue
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

class BooleanLexer : Lexer {
    private val regex = RegexPatterns.BOOLEAN_REGEX

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { result ->
            if (isInQuotes(result, code)) {
                null
            } else {
                createToken(result, code, BooleanValue)
            }
        }.toList()
    }
}
