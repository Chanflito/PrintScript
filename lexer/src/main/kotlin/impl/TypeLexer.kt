package lexer.impl

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken
import lexer.util.isInQuotes

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