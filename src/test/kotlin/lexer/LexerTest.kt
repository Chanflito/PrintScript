package lexer

import common.token.Position
import common.token.Token
import common.token.TokenType
import lexer.imp.KeywordLexer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class LexerTest() {
    @Test
    fun test001_letKeywordWithNormalInput() {
        val input = """let a : string = 'hola'""";
        val letLexer = KeywordLexer(mapOf("let" to TokenType.LET_KEYWORD));
        val result = letLexer.splitIntoTokens(input);
        assert(result.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
    }

    @Test
    fun test002_letKeywordWithNoNormalInput() {
        val inputWithDoubleQuote = """let a : string = "00000;;;;;;;;-let 31313131312"""";

        val inputWithSingleQuote = """let a : string = "00000;;;;;;;;-let 31313131312"""";
        val letLexer = KeywordLexer(mapOf("let" to TokenType.LET_KEYWORD));

        val resultWithDoubleQuote = letLexer.splitIntoTokens(inputWithDoubleQuote)

        val resultWithSingleQuote = letLexer.splitIntoTokens(inputWithSingleQuote);

        assert(resultWithDoubleQuote.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithDoubleQuote.size);

        assert(resultWithSingleQuote.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithSingleQuote.size);
    }
}