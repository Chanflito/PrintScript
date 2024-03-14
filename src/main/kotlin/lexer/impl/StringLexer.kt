package lexer.impl

import common.token.Token
import common.token.ValueString
import lexer.Lexer
import lexer.util.RegexPatterns
import lexer.util.createToken

class StringLexer : Lexer {
    //Includes the quotes expresion?
    private val regex = RegexPatterns.QUOTES_REGEX
    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).map { result ->
            createToken(result, code, ValueString)
        }.toList()
    }
}