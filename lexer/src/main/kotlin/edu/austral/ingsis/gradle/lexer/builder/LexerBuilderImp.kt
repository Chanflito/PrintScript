package edu.austral.ingsis.gradle.lexer.builder

import lexer.edu.austral.ingsis.gradle.lexer.Lexer
import lexer.edu.austral.ingsis.gradle.lexer.LexerBuilder
import edu.austral.ingsis.gradle.lexer.impl.ComposeLexer

class LexerBuilderImp (private val lexers:List<Lexer>) : LexerBuilder {
    override fun withLexer(lexer: Lexer): LexerBuilder {
        val updatedLexers = lexers + lexer //concatenate the new lexer with list of lexers.
        return LexerBuilderImp(updatedLexers)
    }

    override fun build(): ComposeLexer {
        return ComposeLexer(lexers);
    }
}