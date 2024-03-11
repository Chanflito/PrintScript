package lexer

import common.token.Position
import common.token.Token
import common.token.TokenType
import lexer.imp.IdentifierLexer
import lexer.imp.KeywordLexer
import lexer.imp.TypeLexer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class LexerTest() {
    @Test
    fun test001_letKeyword() {
        val input = """let a : string = 'hola'""";
        val letLexer = KeywordLexer(mapOf("let" to TokenType.LET_KEYWORD));
        val result = letLexer.splitIntoTokens(input);
        assert(result.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
    }

    @Test
    fun test002_letKeyword() {
        val inputWithDoubleQuote = """let a : string = "00000;;;;;;;;let 31313131312"""";
        val inputWithSingleQuote = """let a : string = "00000;;;;;;;;let 31313131312"""";
        val letLexer = KeywordLexer(mapOf("let" to TokenType.LET_KEYWORD));

        val resultWithDoubleQuote = letLexer.splitIntoTokens(inputWithDoubleQuote)

        val resultWithSingleQuote = letLexer.splitIntoTokens(inputWithSingleQuote);

        assert(resultWithDoubleQuote.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithDoubleQuote.size);

        assert(resultWithSingleQuote.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithSingleQuote.size);
    }

    @Test
    fun test003_identifier(){
        val input= """let n : number = 5; """
        val identifierLexer= IdentifierLexer(listOf("let","println","number","string"))

        val result = identifierLexer.splitIntoTokens(input);
        assert(result.contains(Token("n", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6))))
        assertEquals(1, result.size);
    }

    @Test
    fun test004_typeNumber(){
        val input= """let n : number = 19; """
        val typeLexer = TypeLexer(
            mapOf("number" to TokenType.TYPE_NUMBER)
        )
        val result = typeLexer.splitIntoTokens(input)
        assert(result.contains(Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15))))
        assertEquals(1, result.size)
    }

}