package lexer.impl

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken
import lexer.util.isInQuotes

class IdentifierLexer(private val constraints: List<String>) : Lexer {
    private val regex = RegexPatterns.IDENTIFIER_REGEX

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { matchResult ->
            if (isValid(matchResult, code)) {
                createToken(matchResult, code, TokenType.IDENTIFIER)
            } else null
        }.toList()
    }

    private fun isValid(matchResult: MatchResult, code: String) =
        !constraints.contains(matchResult.value) && !isInQuotes(matchResult, code)
}