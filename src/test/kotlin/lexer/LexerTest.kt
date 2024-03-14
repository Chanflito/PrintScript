package lexer

import common.token.Position
import common.token.Token
import common.token.TokenType
import lexer.impl.*
import lexer.util.createComposeLexer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LexerTest {
    @Test
    fun test001_letKeyword() {
        val input = "let a : string = 'hola'"
        val letLexer = KeywordLexer(mapOf("let" to TokenType.LET_KEYWORD))
        val result = letLexer.splitIntoTokens(input)
        assert(result.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
    }

    @Test
    fun test002_letKeyword() {
        val inputWithDoubleQuote = "let a : string = \"00000;;;;;;;;let 31313131312\""
        val inputWithSingleQuote = "let a : string = '00000;;;;;;;;let 31313131312'"
        val letLexer = KeywordLexer(mapOf("let" to TokenType.LET_KEYWORD))

        val resultWithDoubleQuote = letLexer.splitIntoTokens(inputWithDoubleQuote)

        val resultWithSingleQuote = letLexer.splitIntoTokens(inputWithSingleQuote)

        assert(resultWithDoubleQuote.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithDoubleQuote.size)

        assert(resultWithSingleQuote.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithSingleQuote.size)
    }

    @Test
    fun test003_identifier() {
        val input = "let n : number = 5; "
        val identifierLexer = IdentifierLexer(listOf("let", "println", "number", "string"))

        val result = identifierLexer.splitIntoTokens(input)
        assert(result.contains(Token("n", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6))))
        assertEquals(1, result.size)
    }

    @Test
    fun test004_typeNumber() {
        val input = " let n : number = 19; "
        val typeLexer = TypeLexer(
            mapOf("number" to TokenType.TYPE_NUMBER)
        )
        val result = typeLexer.splitIntoTokens(input)
        assert(result.contains(Token("number", TokenType.TYPE_NUMBER, Position(1, 10), Position(1, 16))))
        assertEquals(1, result.size)
    }

    @Test
    fun test005_operator() {
        val input = " let n : number = (19 +5) ; "
        val operatorLexer = OperatorLexer()
        val result = operatorLexer.splitIntoTokens(input)
        assert(result.contains(Token(":", TokenType.COLON, Position(1, 8), Position(1, 9))))
        assert(result.contains(Token("=", TokenType.ASSIGNATION, Position(1, 17), Position(1, 18))))
        assert(result.contains(Token("(", TokenType.LEFT_PARENTHESIS, Position(1, 19), Position(1, 20))))
        assert(result.contains(Token("+", TokenType.PLUS, Position(1, 23), Position(1, 24))))
        assert(result.contains(Token(")", TokenType.RIGHT_PARENTHESIS, Position(1, 25), Position(1, 26))))
        assert(result.contains(Token(";", TokenType.SEMI_COLON, Position(1, 27), Position(1, 28))))
        assertEquals(6, result.size)
    }

    @Test
    fun test006_string() {
        val input = " let n : string = \"hola\"; "
        val stringLexer = StringLexer()
        val result = stringLexer.splitIntoTokens(input)
        assert(
            result.contains(Token("\"hola\"", TokenType.VALUE_STRING, Position(1, 19), Position(1, 25)))
        )
        assertEquals(1, result.size)
    }

    @Test
    fun test007_number() {
        val input = "let n : number = 19;"
        val numberLexer = NumberLexer()
        val result = numberLexer.splitIntoTokens(input)

        assert(
            result.contains(Token("19", TokenType.VALUE_NUMBER, Position(1, 18), Position(1, 20)))
        )
        assertEquals(1, result.size)
    }

    @Test
    fun test008_composeLexerWithNormalCase() {
        val input = "let a : number = 5; \n println(a)"
        val composeLexer = createComposeLexer()
        val result = composeLexer.splitIntoTokens(input)
        val expected = listOf(
            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
            Token("a", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6)),
            Token(":", TokenType.COLON, Position(1, 7), Position(1, 8)),
            Token("number", TokenType.TYPE_NUMBER, Position(1, 9), Position(1, 15)),
            Token("=", TokenType.ASSIGNATION, Position(1, 16), Position(1, 17)),
            Token("5", TokenType.VALUE_NUMBER, Position(1, 18), Position(1, 19)),
            Token(";", TokenType.SEMI_COLON, Position(1, 19), Position(1, 20)),
            Token("println", TokenType.PRINTLN_KEYWORD, Position(2, 2), Position(2, 9)),
            Token("(", TokenType.LEFT_PARENTHESIS, Position(2, 9), Position(2, 10)),
            Token("a", TokenType.IDENTIFIER, Position(2, 10), Position(2, 11)),
            Token(")", TokenType.RIGHT_PARENTHESIS, Position(2, 11), Position(2, 12))
        )
        assertEquals(expected, result)
    }

    @Test
    fun test009_composeLexerWithEdgeCase() {
        val input = "let variable : string = + 5 \"let\" + 1 + \"println\" + \"aaalet1\"; "
        val composeLexer = createComposeLexer()

        val result = composeLexer.splitIntoTokens(input)

        val expected = listOf(
            Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
            Token("variable", TokenType.IDENTIFIER, Position(1, 5), Position(1, 13)),
            Token(":", TokenType.COLON, Position(1, 14), Position(1, 15)),
            Token("string", TokenType.TYPE_STRING, Position(1, 16), Position(1, 22)),
            Token("=", TokenType.ASSIGNATION, Position(1, 23), Position(1, 24)),
            Token("+", TokenType.PLUS, Position(1, 25), Position(1, 26)),
            Token("5", TokenType.VALUE_NUMBER, Position(1, 27), Position(1, 28)),
            Token("\"let\"", TokenType.VALUE_STRING, Position(1, 29), Position(1, 34)),
            Token("+", TokenType.PLUS, Position(1, 35), Position(1, 36)),
            Token("1", TokenType.VALUE_NUMBER, Position(1, 37), Position(1, 38)),
            Token("+", TokenType.PLUS, Position(1, 39), Position(1, 40)),
            Token("\"println\"", TokenType.VALUE_STRING, Position(1, 41), Position(1, 50)),
            Token("+", TokenType.PLUS, Position(1, 51), Position(1, 52)),
            Token("\"aaalet1\"", TokenType.VALUE_STRING, Position(1, 53), Position(1, 62)),
            Token(";", TokenType.SEMI_COLON, Position(1, 62), Position(1, 63))
        )
        assertEquals(expected, result)
    }
}
