package edu.austral.ingsis.gradle.lexer

import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.NumberType
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import edu.austral.ingsis.gradle.lexer.impl.IdentifierLexer
import edu.austral.ingsis.gradle.lexer.impl.KeywordLexer
import edu.austral.ingsis.gradle.lexer.impl.NumberLexer
import edu.austral.ingsis.gradle.lexer.impl.OperatorLexer
import edu.austral.ingsis.gradle.lexer.impl.StringLexer
import edu.austral.ingsis.gradle.lexer.impl.TypeLexer
import edu.austral.ingsis.gradle.lexer.util.compareFiles
import edu.austral.ingsis.gradle.lexer.util.convertFileToString
import edu.austral.ingsis.gradle.lexer.util.input_001
import edu.austral.ingsis.gradle.lexer.util.input_002_doubleQuote
import edu.austral.ingsis.gradle.lexer.util.input_002_singleQuote
import edu.austral.ingsis.gradle.lexer.util.input_003
import edu.austral.ingsis.gradle.lexer.util.input_004
import edu.austral.ingsis.gradle.lexer.util.input_005
import edu.austral.ingsis.gradle.lexer.util.input_006
import edu.austral.ingsis.gradle.lexer.util.input_007
import edu.austral.ingsis.gradle.lexer.util.input_008
import edu.austral.ingsis.gradle.lexer.util.input_009
import edu.austral.ingsis.gradle.lexer.util.input_010
import edu.austral.ingsis.gradle.lexer.util.input_011
import edu.austral.ingsis.gradle.lexer.util.operators
import edu.austral.ingsis.gradle.lexer.util.output_001
import edu.austral.ingsis.gradle.lexer.util.output_002_doubleQuote
import edu.austral.ingsis.gradle.lexer.util.output_002_singleQuote
import edu.austral.ingsis.gradle.lexer.util.output_003
import edu.austral.ingsis.gradle.lexer.util.output_004
import edu.austral.ingsis.gradle.lexer.util.output_005
import edu.austral.ingsis.gradle.lexer.util.output_006
import edu.austral.ingsis.gradle.lexer.util.output_007
import edu.austral.ingsis.gradle.lexer.util.output_008
import edu.austral.ingsis.gradle.lexer.util.output_009
import edu.austral.ingsis.gradle.lexer.util.output_010
import edu.austral.ingsis.gradle.lexer.util.output_011
import edu.austral.ingsis.gradle.lexer.util.writeTokensToFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class LexerTest {
    @Test
    fun test001_letKeyword() {
        val letLexer = KeywordLexer(mapOf("let" to LetKeyword))
        val result = letLexer.splitIntoTokens(input_001)
        assert(result.contains(output_001))
    }

    // let a : string = "00000;;;;;;;;let 31313131312"
    // let a : string = '00000;;;;;;;;let 31313131312'
    @Test
    fun test002_letKeyword() {
        val letLexer = KeywordLexer(mapOf("let" to LetKeyword))

        val resultWithDoubleQuote = letLexer.splitIntoTokens(input_002_doubleQuote)

        val resultWithSingleQuote = letLexer.splitIntoTokens(input_002_singleQuote)

        assert(resultWithDoubleQuote.contains(output_002_doubleQuote))
        assertEquals(1, resultWithDoubleQuote.size)

        assert(resultWithSingleQuote.contains(output_002_singleQuote))
        assertEquals(1, resultWithSingleQuote.size)
    }

    // let n : number = 5;
    @Test
    fun test003_identifier() {
        val identifierLexer = IdentifierLexer(listOf("let", "println", "number", "string"))

        val result = identifierLexer.splitIntoTokens(input_003)
        assert(result.contains(output_003))
        assertEquals(1, result.size)
    }

    // let n : number = 19;
    @Test
    fun test004_typeNumber() {
        val typeLexer =
            TypeLexer(
                mapOf("number" to NumberType),
            )
        val result = typeLexer.splitIntoTokens(input_004)
        assert(result.contains(output_004))
        assertEquals(1, result.size)
    }

    // let n : number = (19 +5) ;
    @Test
    fun test005_operator() {
        val operatorLexer = OperatorLexer(operators)
        val result = operatorLexer.splitIntoTokens(input_005)
        assert(result.containsAll(output_005))
        assertEquals(6, result.size)
    }

    // let n : string = "hola";
    @Test
    fun test006_string() {
        val stringLexer = StringLexer()
        val result = stringLexer.splitIntoTokens(input_006)
        assert(result.contains(output_006))
        assertEquals(1, result.size)
    }

    // let n : number = 19;
    @Test
    fun test007_number() {
        val numberLexer = NumberLexer()
        val result = numberLexer.splitIntoTokens(input_007)
        assert(result.contains(output_007))
        assertEquals(1, result.size)
    }

    // let a : number = 5;
    // println(a)
    @Test
    fun test008_composeLexerWithNormalCase() {
        val composeLexer = LexerDirector().createComposeLexer("1.0")
        val result = composeLexer.splitIntoTokens(input_008)
        assertEquals(output_008, result)
        // assertEquals(compareTokens(expected, result), true);
    }

    // let variable : string = + 5 "let" + 1 + "println" + "aaalet1";
    @Test
    fun test009_composeLexerWithEdgeCase() {
        val composeLexer = LexerDirector().createComposeLexer("1.0")
        val result = composeLexer.splitIntoTokens(input_009)
        assertEquals(output_009, result)
    }

    // let name : string = "Joe";
    // let lastName : string = "Doe" ;
    // println(name + " " + lastName);
    @Test
    fun test010_composeLexer() {
        val composeLexer = LexerDirector().createComposeLexer("1.0")
        val result = composeLexer.splitIntoTokens(input_010)
        assertEquals(output_010, result)
    }

    // let a: number = 12;
    // let b: number = 4;
    // a = a / b;
    @Test
    fun test011_composeLexer() {
        val composeLexer = LexerDirector().createComposeLexer("1.0")
        val result = composeLexer.splitIntoTokens(input_011)
        assertEquals(output_011, result)
    }

    // TODO: Add more tests with this new version.
    @Test
    fun test012_composeLexerWithIfElse() {
        val composeLexer = LexerDirector().createComposeLexer("1.1")
        val input = convertFileToString("src/test/resources/input/input_012.txt")
        val result = composeLexer.splitIntoTokens(input)
        val output = writeTokensToFile(result, "src/test/resources/output/output_012.txt")
        assertTrue(compareFiles(output, "src/test/resources/output/expected_012.txt"))
    }

    @Test
    fun test013_composeLexerWithConstKeywordAndReadInput() {
        val composeLexer = LexerDirector().createComposeLexer("1.1")
        val input = convertFileToString("src/test/resources/input/input_013.txt")
        val result = composeLexer.splitIntoTokens(input)
        val output = writeTokensToFile(result, "src/test/resources/output/output_013.txt")
        assertTrue(compareFiles(output, "src/test/resources/output/expected_013.txt"))
    }

    @Test
    fun test013_composeLexerWithReadEnv() {
        val composeLexer = LexerDirector().createComposeLexer("1.1")
        val input = convertFileToString("src/test/resources/input/input_014.txt")
        val result = composeLexer.splitIntoTokens(input)
        val output = writeTokensToFile(result, "src/test/resources/output/output_014.txt")
        assertTrue(compareFiles(output, "src/test/resources/output/expected_014.txt"))
    }
}
