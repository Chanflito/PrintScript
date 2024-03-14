package lexer.builder

import lexer.Lexer
import lexer.LexerBuilder
import lexer.impl.ComposeLexer

class LexerBuilderImp (private val composeLexer: ComposeLexer) : LexerBuilder{
    override fun withLexer(lexer: Lexer): LexerBuilder {
        return this.apply {
            composeLexer.addLexer(lexer)
        }
    }

    override fun build(): ComposeLexer {
        return composeLexer
    }
}