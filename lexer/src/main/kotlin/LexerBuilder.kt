package lexer

interface LexerBuilder {
    fun withLexer(lexer: Lexer): LexerBuilder

    fun build():Lexer
}