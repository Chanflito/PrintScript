package lexer.builder

import lexer.Lexer
import lexer.LexerBuilder
import lexer.imp.ComposeLexer

class LexerBuilderImp (private val composeLexer: ComposeLexer) : LexerBuilder{
    override fun withLexer(lexer: Lexer): LexerBuilder {
        return LexerBuilderImp(composeLexer.addLexer(lexer))
    }

    override fun build(): ComposeLexer {
        return composeLexer;
    }
}