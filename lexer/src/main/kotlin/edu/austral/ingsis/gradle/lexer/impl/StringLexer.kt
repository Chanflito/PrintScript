package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.ValueString
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken

class StringLexer : Lexer {
    // Includes the quotes expresion?
    private val regex = RegexPatterns.QUOTES_REGEX

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).map { result ->
            createToken(result, code, ValueString)
        }.toList()
    }
}
