package edu.austral.ingisis.gradle.app

import edu.austral.ingsis.gradle.interpreter.Interpreter
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PrintScriptTest {
    @Test
    fun test_HelloWorldPrint() {
        val code = "let a : string = \"Hello, World!\"; println(a);" // Print "Hello, World!"
        val tokens = LexerDirector().createComposeLexer("1.0").splitIntoTokens(code)
        val ast = createComposeParser().parse(InputContext(tokens, 0)).first
        val result = Interpreter().interpret(ast)
        assertEquals("Hello, World!", result[0])
    }

    @Test
    fun test_Addition() {
        val code = "let a : number = 1; let b : number = 2; println(a + b);" // Print 3
        val tokens = LexerDirector().createComposeLexer("1.0").splitIntoTokens(code)
        val ast = createComposeParser().parse(InputContext(tokens, 0)).first
        val result = Interpreter().interpret(ast)
        assertEquals(3.0, result[0])
    }

    @Test
    fun test_Subtraction() {
        val code = "let a : number = 1; let b : number = 2; println(a - b);" // Print -1
        val tokens = LexerDirector().createComposeLexer("1.0").splitIntoTokens(code)
        val ast = createComposeParser().parse(InputContext(tokens, 0)).first
        val result = Interpreter().interpret(ast)
        assertEquals(-1.0, result[0])
    }

    @Test
    fun test_Multiplication() {
        val code = "let a : number = 2; let b : number = 3; println(a * b);" // Print 6
        val tokens = LexerDirector().createComposeLexer("1.0").splitIntoTokens(code)
        val ast = createComposeParser().parse(InputContext(tokens, 0)).first
        val result = Interpreter().interpret(ast)
        assertEquals(6.0, result[0])
    }

    @Test
    fun test_Division() {
        val code = "let a : number = 6; let b : number = 3; println(a / b);" // Print 2
        val tokens = LexerDirector().createComposeLexer("1.0").splitIntoTokens(code)
        val ast = createComposeParser().parse(InputContext(tokens, 0)).first
        val result = Interpreter().interpret(ast)
        assertEquals(2.0, result[0])
    }

    @Test
    fun test_StringAddition() {
        val code = "let a : string = \"Hello, \"; let b : string = \"World!\"; println(a + b);" // Print "Hello, World!"
        val tokens = LexerDirector().createComposeLexer("1.0").splitIntoTokens(code)
        val ast = createComposeParser().parse(InputContext(tokens, 0)).first
        val result = Interpreter().interpret(ast)
        assertEquals("Hello, World!", result[0])
    }
}
