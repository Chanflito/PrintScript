package lexer.impl

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken
import lexer.util.isInQuotes

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
            if (isInQuotes(result, code)) null
            else createToken(result, code, tokens[result.value] ?: TokenType.UNKNOWN)
        }.toList()
    }
}
