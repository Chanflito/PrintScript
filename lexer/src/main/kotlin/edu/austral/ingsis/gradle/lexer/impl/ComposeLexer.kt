package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.lexer.Lexer

class ComposeLexer(private val lexers: List<Lexer>) : Lexer {
    override fun splitIntoTokens(code: String): List<Token> {
        return lexers.flatMap { lexer ->
            lexer.splitIntoTokens(code)
        }.sortedBy { token ->
            token.startPosition // Ordenar por la posición de inicio del token
        }
    }

}