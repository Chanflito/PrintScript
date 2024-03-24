package edu.austral.ingsis.gradle.lexer

import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.TypeNumber
import edu.austral.ingsis.gradle.lexer.impl.*
import edu.austral.ingsis.gradle.lexer.util.createComposeLexer
import edu.austral.ingsis.gradle.lexer.utils.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import edu.austral.ingsis.gradle.lexer.impl.NumberLexer


class LexerTest{
    @Test
    //let a : string = 'hola'
    fun test001_letKeyword() {
        val letLexer = KeywordLexer(mapOf("let" to LetKeyword))
        val result = letLexer.splitIntoTokens(input_001)
        assert(result.contains(output_001))
    }

    @Test
    //let a : string = "00000;;;;;;;;let 31313131312"
    //let a : string = '00000;;;;;;;;let 31313131312'
    fun test002_letKeyword() {
        val letLexer = KeywordLexer(mapOf("let" to LetKeyword))

        val resultWithDoubleQuote = letLexer.splitIntoTokens(input_002_doubleQuote)

        val resultWithSingleQuote = letLexer.splitIntoTokens(input_002_singleQuote)

        assert(resultWithDoubleQuote.contains(output_002_doubleQuote))
        assertEquals(1, resultWithDoubleQuote.size)

        assert(resultWithSingleQuote.contains(output_002_singleQuote))
        assertEquals(1, resultWithSingleQuote.size)
    }

    @Test
    //let n : number = 5;
    fun test003_identifier() {
        val identifierLexer = IdentifierLexer(listOf("let", "println", "number", "string"))

        val result = identifierLexer.splitIntoTokens(input_003)
        assert(result.contains(output_003))
        assertEquals(1, result.size)
    }

    @Test
    //let n : number = 19;
    fun test004_typeNumber() {
        val typeLexer = TypeLexer(
            mapOf("number" to TypeNumber)
        )
        val result = typeLexer.splitIntoTokens(input_004)
        assert(result.contains(output_004))
        assertEquals(1, result.size)
    }

    @Test
    //let n : number = (19 +5) ;
    fun test005_operator() {
        val operatorLexer = OperatorLexer()
        val result = operatorLexer.splitIntoTokens(input_005)
        assert(result.containsAll(output_005))
        assertEquals(6, result.size)
    }

    @Test
    //let n : string = "hola";
    fun test006_string() {
        val stringLexer = StringLexer()
        val result = stringLexer.splitIntoTokens(input_006)
        assert(result.contains(output_006))
        assertEquals(1, result.size)
    }

    @Test
    //let n : number = 19;
    fun test007_number() {
        val numberLexer = NumberLexer()
        val result = numberLexer.splitIntoTokens(input_007)
        assert(result.contains(output_007))
        assertEquals(1, result.size)
    }

    @Test
    //let a : number = 5;
    //println(a)
    fun test008_composeLexerWithNormalCase() {
        val composeLexer = createComposeLexer()
        val result = composeLexer.splitIntoTokens(input_008)
        assertEquals(output_008, result)
        //assertEquals(compareTokens(expected, result), true);
    }

    @Test
    //let variable : string = + 5 "let" + 1 + "println" + "aaalet1";
    fun test009_composeLexerWithEdgeCase() {
        val composeLexer = createComposeLexer()
        val result = composeLexer.splitIntoTokens(input_009)
        assertEquals(output_009, result)
    }

    @Test
    //let name : string = "Joe";
    // let lastName : string = "Doe" ;
    // println(name + " " + lastName);
    fun test010_composeLexer() {
        val composeLexer = createComposeLexer()
        val result = composeLexer.splitIntoTokens(input_010)
        assertEquals(output_010, result)
    }

    @Test
    //let a: number = 12;
    //let b: number = 4;
    //a = a / b;
    fun test011_composeLexer() {
        val composeLexer = createComposeLexer()
        val result = composeLexer.splitIntoTokens(input_011)
        assertEquals(output_011, result)
    }
}