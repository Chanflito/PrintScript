package lexer

import common.token.Position
import common.token.Token
import common.token.TokenType
import lexer.imp.*
import lexer.util.createComposeLexer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class LexerTest() {
    @Test
    fun test001_letKeyword() {
        val input = """let a : string = 'hola'"""
        val letLexer = KeywordLexer(mapOf("let" to TokenType.LET_KEYWORD))
        val result = letLexer.splitIntoTokens(input)
        assert(result.contains(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4))))
    }

    @Test
    fun test002_letKeyword() {
        val inputWithDoubleQuote = """let a : string = "00000;;;;;;;;let 31313131312""""
        val inputWithSingleQuote = """let a : string = '00000;;;;;;;;let 31313131312'"""
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
        val input = """let n : number = 5; """
        val identifierLexer = IdentifierLexer(listOf("let", "println", "number", "string"))

        val result = identifierLexer.splitIntoTokens(input)
        assert(result.contains(Token("n", TokenType.IDENTIFIER, Position(1, 5), Position(1, 6))))
        assertEquals(1, result.size)
    }

    @Test
    fun test004_typeNumber() {
        val input = """ let n : number = 19; """
        val typeLexer = TypeLexer(
            mapOf("number" to TokenType.TYPE_NUMBER)
        )
        val result = typeLexer.splitIntoTokens(input)
        assert(result.contains(Token("number", TokenType.TYPE_NUMBER, Position(1, 10), Position(1, 16))))
        assertEquals(1, result.size)
    }

    @Test
    fun test005_operator() {
        val input = """ let n : number = (19 +5) ; """
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
    fun test006_string(){
        val input = """ let n : string = "hola"; """
        val stringLexer = StringLexer()
        val result = stringLexer.splitIntoTokens(input)
        assert(result.contains(Token("\"hola\"", TokenType.VALUE_STRING, Position(1, 19), Position(1, 25)))
        )
        assertEquals(1, result.size)
    }

    @Test
    fun test008_number(){
        val input = """let n : number = 19;"""
        val numberLexer = NumberLexer()
        val result = numberLexer.splitIntoTokens(input)

        assert(result.contains(Token("19", TokenType.VALUE_NUMBER, Position(1, 18), Position(1, 20)))
        )
        assertEquals(1, result.size)
    }
}