package lexer

import common.token.*
import lexer.impl.*
import lexer.util.createComposeLexer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class LexerTest() {
    @Test
    fun test001_letKeyword() {
        val input = """let a : string = 'hola'"""
        val letLexer = KeywordLexer(mapOf("let" to LetKeyword))
        val result = letLexer.splitIntoTokens(input)
        assert(result.contains(Token("let", LetKeyword, Position(1, 1), Position(1, 4))))
    }

    @Test
    fun test002_letKeyword() {
        val inputWithDoubleQuote = """let a : string = "00000;;;;;;;;let 31313131312""""
        val inputWithSingleQuote = """let a : string = '00000;;;;;;;;let 31313131312'"""
        val letLexer = KeywordLexer(mapOf("let" to LetKeyword))

        val resultWithDoubleQuote = letLexer.splitIntoTokens(inputWithDoubleQuote)

        val resultWithSingleQuote = letLexer.splitIntoTokens(inputWithSingleQuote)

        assert(resultWithDoubleQuote.contains(Token("let", LetKeyword, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithDoubleQuote.size)

        assert(resultWithSingleQuote.contains(Token("let", LetKeyword, Position(1, 1), Position(1, 4))))
        assertEquals(1, resultWithSingleQuote.size)
    }

    @Test
    fun test003_identifier() {
        val input = """let n : number = 5; """
        val identifierLexer = IdentifierLexer(listOf("let", "println", "number", "string"))

        val result = identifierLexer.splitIntoTokens(input)
        assert(result.contains(Token("n", Identifier, Position(1, 5), Position(1, 6))))
        assertEquals(1, result.size)
    }

    @Test
    fun test004_typeNumber() {
        val input = """ let n : number = 19; """
        val typeLexer = TypeLexer(
            mapOf("number" to TypeNumber)
        )
        val result = typeLexer.splitIntoTokens(input)
        assert(result.contains(Token("number", TypeNumber, Position(1, 10), Position(1, 16))))
        assertEquals(1, result.size)
    }

    @Test
    fun test005_operator() {
        val input = """ let n : number = (19 +5) ; """
        val operatorLexer = OperatorLexer()
        val result = operatorLexer.splitIntoTokens(input)
        assert(result.contains(Token(":", Colon, Position(1, 8), Position(1, 9))))
        assert(result.contains(Token("=", Assignation, Position(1, 17), Position(1, 18))))
        assert(result.contains(Token("(", LeftParenthesis, Position(1, 19), Position(1, 20))))
        assert(result.contains(Token("+", Plus, Position(1, 23), Position(1, 24))))
        assert(result.contains(Token(")", RightParenthesis, Position(1, 25), Position(1, 26))))
        assert(result.contains(Token(";", SemiColon, Position(1, 27), Position(1, 28))))
        assertEquals(6, result.size)
    }

    @Test
    fun test006_string() {
        val input = """ let n : string = "hola"; """
        val stringLexer = StringLexer()
        val result = stringLexer.splitIntoTokens(input)
        assert(result.contains(Token("\"hola\"", ValueString, Position(1, 19), Position(1, 25))))
        assertEquals(1, result.size)
    }

    @Test
    fun test007_number() {
        val input = """let n : number = 19;"""
        val numberLexer = NumberLexer()
        val result = numberLexer.splitIntoTokens(input)


        assert(result.contains(Token("19", ValueNumber, Position(1, 18), Position(1, 20))))
        assertEquals(1, result.size)
    }

    @Test
    fun test008_composeLexerWithNormalCase() {
        val input = "let a : number = 5; \n println(a)"
        val composeLexer = createComposeLexer();
        val result = composeLexer.splitIntoTokens(input);
        val expected = listOf(
            Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
            Token("a", Identifier, Position(1, 5), Position(1, 6)),
            Token(":", Colon, Position(1, 7), Position(1, 8)),
            Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
            Token("=", Assignation, Position(1, 16), Position(1, 17)),
            Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
            Token(";", SemiColon, Position(1, 19), Position(1, 20)),
            Token("println", PrintlnKeyword, Position(2, 2), Position(2, 9)),
            Token("(", LeftParenthesis, Position(2, 9), Position(2, 10)),
            Token("a", Identifier, Position(2, 10), Position(2, 11)),
            Token(")", RightParenthesis, Position(2, 11), Position(2, 12))

        );
        assertEquals(expected, result)
        //assertEquals(compareTokens(expected, result), true);
    }

    @Test
    fun test09_composeLexerWithEdgeCase() {
        val input = """let variable : string = + 5 "let" + 1 + "println" + "aaalet1"; """
        val composeLexer = createComposeLexer();

        val result = composeLexer.splitIntoTokens(input);
        val expected = listOf(
            Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
            Token("variable", Identifier, Position(1, 5), Position(1, 13)),
            Token(":", Colon, Position(1, 14), Position(1, 15)),
            Token("string", TypeString, Position(1, 16), Position(1, 22)),
            Token("=", Assignation, Position(1, 23), Position(1, 24)),
            Token("+", Plus, Position(1, 25), Position(1, 26)),
            Token("5", ValueNumber, Position(1, 27), Position(1, 28)),
            Token("\"let\"", ValueString, Position(1, 29), Position(1, 34)),
            Token("+", Plus, Position(1, 35), Position(1, 36)),
            Token("1", ValueNumber, Position(1, 37), Position(1, 38)),
            Token("+", Plus, Position(1, 39), Position(1, 40)),
            Token("\"println\"", ValueString, Position(1, 41), Position(1, 50)),
            Token("+", Plus, Position(1, 51), Position(1, 52)),
            Token("\"aaalet1\"", ValueString, Position(1, 53), Position(1, 62)),
            Token(";", SemiColon, Position(1, 62), Position(1, 63))
        );
        assertEquals(expected, result);
    }

    @Test
    fun test010_composeLexer(){
        val input = "let name : string = \"Joe\";\n let lastName : string = \"Doe\" ;\n println(name + \" \" + lastName);"
        val composeLexer = createComposeLexer();
        val result = composeLexer.splitIntoTokens(input);
        val expected = listOf(
            Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
            Token("name", Identifier, Position(1, 5), Position(1, 9)),
            Token(":", Colon, Position(1, 10), Position(1, 11)),
            Token("string", TypeString, Position(1, 12), Position(1, 18)),
            Token("=", Assignation, Position(1, 19), Position(1, 20)),
            Token("\"Joe\"", ValueString, Position(1, 21), Position(1, 26)),
            Token(";", SemiColon, Position(1, 26), Position(1, 27)),
            Token("let", LetKeyword, Position(2, 2), Position(2, 5)),
            Token("lastName", Identifier, Position(2, 6), Position(2, 14)),
            Token(":", Colon, Position(2, 15), Position(2, 16)),
            Token("string", TypeString, Position(2, 17), Position(2, 23)),
            Token("=", Assignation, Position(2, 24), Position(2, 25)),
            Token("\"Doe\"", ValueString, Position(2, 26), Position(2, 31)),
            Token(";", SemiColon, Position(2, 32), Position(2, 33)),
            Token("println", PrintlnKeyword, Position(3, 2), Position(3, 9)),
            Token("(", LeftParenthesis, Position(3, 9), Position(3, 10)),
            Token("name", Identifier, Position(3, 10), Position(3, 14)),
            Token("+", Plus, Position(3, 15), Position(3, 16)),
            Token("\" \"", ValueString, Position(3, 17), Position(3, 20)),
            Token("+", Plus, Position(3, 21), Position(3, 22)),
            Token("lastName", Identifier, Position(3, 23), Position(3, 31)),
            Token(")", RightParenthesis, Position(3, 31), Position(3, 32)),
            Token(";", SemiColon, Position(3, 32), Position(3, 33)
        ));
        assertEquals(expected, result);
    }

    @Test
    fun test011_composeLexer(){
        val input = "let a: number = 12;\n" +
                "let b: number = 4;\n" +
                "a = a / b;"
        val composeLexer = createComposeLexer();
        val result = composeLexer.splitIntoTokens(input);
        val expected = listOf(
            Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
            Token("a", Identifier, Position(1, 5), Position(1, 6)),
            Token(":", Colon, Position(1, 6), Position(1, 7)),
            Token("number", TypeNumber, Position(1, 8), Position(1, 14)),
            Token("=", Assignation, Position(1, 15), Position(1, 16)),
            Token("12", ValueNumber, Position(1, 17), Position(1, 19)),
            Token(";", SemiColon, Position(1, 19), Position(1, 20)),
            Token("let", LetKeyword, Position(2, 1), Position(2, 4)),
            Token("b", Identifier, Position(2, 5), Position(2, 6)),
            Token(":", Colon, Position(2, 6), Position(2, 7)),
            Token("number", TypeNumber, Position(2, 8), Position(2, 14)),
            Token("=", Assignation, Position(2, 15), Position(2, 16)),
            Token("4", ValueNumber, Position(2, 17), Position(2, 18)),
            Token(";", SemiColon, Position(2, 18), Position(2, 19)),
            Token("a", Identifier, Position(3, 1), Position(3, 2)),
            Token("=", Assignation, Position(3, 3), Position(3, 4)),
            Token("a", Identifier, Position(3, 5), Position(3, 6)),
            Token("/", Divide, Position(3, 7), Position(3, 8)),
            Token("b", Identifier, Position(3, 9), Position(3, 10)),
            Token(";", SemiColon, Position(3, 10), Position(3, 11)
        ));
        assertEquals(expected, result);
    }
}
