package edu.austral.ingsis.gradle.lexer.impl

import common.token.Token
import common.token.TokenType
import lexer.edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

class TypeLexer(private val tokens: Map<String, TokenType>) : Lexer {
    private val regex = RegexPatterns.TYPE_REGEX(tokens)

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { result ->
            if (!isInQuotes(result, code)) {
                if (tokens.containsKey(result.value)) {
                    createToken(result, code, tokens.getValue(result.value))
                } else null
            } else null
        }.toList()
    }
}