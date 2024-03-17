package lexer.impl

import common.token.Identifier
import common.token.Token
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken
import lexer.util.isInQuotes

class IdentifierLexer(private val constraints: List<String>) : Lexer {
    private val regex = RegexPatterns.IDENTIFIER_REGEX

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { matchResult ->
            if (isMatch(matchResult, code)) {
                createToken(matchResult, code, Identifier)
            } else null
        }.toList()
    }

    private fun isMatch(matchResult: MatchResult, code: String) =
        !constraints.contains(matchResult.value) && !isInQuotes(matchResult, code)
}