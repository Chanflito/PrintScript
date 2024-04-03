package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

/**
 * Lexer implementation for tokenizing code based on user given keywords.
 * @param tokens A map of keywords to their corresponding token types.
 */
class KeywordLexer(
    private val tokens: Map<String, TokenType>,
) : Lexer {
    private val regex = RegexPatterns.KEYWORD_REGEX(tokens)

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { result ->
            // If result.value is not in tokens, return null, else create a token with the result and the token type
            if (isInQuotes(result, code)) {
                null
            } else {
                tokens[result.value]?.let { createToken(result, code, it) }
            }
        }.toList()
    }
}
