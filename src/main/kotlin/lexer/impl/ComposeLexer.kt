package lexer.impl

import common.token.Token
import lexer.Lexer

class ComposeLexer(private val lexers: MutableList<Lexer>) : Lexer {
    override fun splitIntoTokens(code: String): List<Token> {
        return lexers.flatMap { lexer ->
            lexer.splitIntoTokens(code)
        }.sortedBy { token ->
            token.startPosition // Ordenar por la posición de inicio del token
        }
    }

    fun addLexer(lexer: Lexer) {
        lexers.add(lexer)
    }
}