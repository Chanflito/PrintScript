package lexer.builder

import lexer.Lexer
import lexer.LexerBuilder
import lexer.impl.ComposeLexer

class LexerBuilderImp (private val lexers:List<Lexer>) : LexerBuilder{
    override fun withLexer(lexer: Lexer): LexerBuilder {
        val updatedLexers = lexers + lexer //concatenate the new lexer with list of lexers.
        return LexerBuilderImp(updatedLexers)
    }

    override fun build(): ComposeLexer {
        return ComposeLexer(lexers);
    }
}