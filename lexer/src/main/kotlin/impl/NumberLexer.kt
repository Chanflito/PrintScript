package lexer.impl

import common.token.Token
import common.token.ValueNumber
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken
import lexer.util.isInQuotes

class NumberLexer : Lexer {
    private val regex = RegexPatterns.NUMBER_REGEX
    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { result ->
            if (isInQuotes(result, code)) null
            else createToken(result, code, ValueNumber)
        }.toList()
    }
}