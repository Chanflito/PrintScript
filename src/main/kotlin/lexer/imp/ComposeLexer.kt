package lexer.imp

import common.token.Token
import lexer.Lexer

class ComposeLexer(private val lexers: MutableList<Lexer>) : Lexer {
    override fun splitIntoTokens(code: String): List<Token> {
        return lexers.map { lexer ->
            lexer.splitIntoTokens(code)
        }.flatten();
    }
    fun addLexer(lexer:Lexer) {
        lexers.add(lexer);
    }
}