package edu.austral.ingsis.gradle.iterator

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.lexer.Lexer
import java.io.BufferedReader

class LexerIterator(private val lexer: Lexer, private val src: BufferedReader) : Iterator<List<Token>> {
    private val statementIterator = StatementIterator(src)

    override fun hasNext(): Boolean {
        return statementIterator.hasNext()
    }

    override fun next(): List<Token> {
        if (hasNext()) {
            return lexer.splitIntoTokens(statementIterator.next())
        }
        return listOf()
    }
}
