package lexer.edu.austral.ingsis.gradle.lexer

interface LexerBuilder {
    fun withLexer(lexer: Lexer): LexerBuilder

    fun build(): Lexer
}