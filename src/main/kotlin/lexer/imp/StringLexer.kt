package lexer.imp

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.createToken

class StringLexer : Lexer {
    //Includes the quotes expresion?
    private val regex = Regex("""(?<!\\)(["'])(.*?)(?<!\\)\1""")
    override fun splitIntoTokens(code: String): List<Token> {
            return regex.findAll(code).map { result ->
                createToken(result, code, TokenType.VALUE_STRING)
        }.toList()
    }
}