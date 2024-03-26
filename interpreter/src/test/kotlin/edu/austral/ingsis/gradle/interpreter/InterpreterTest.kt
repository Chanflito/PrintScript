package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterTest {
    //    TEST
//    println(5 + 7)
    @Test
    fun test001_directSumAndPrint() {
        val input = ASTNodeImpl("Program", null, ProgramNode, input_001)
        val interpreter = Interpreter()
        val results = interpreter.interpret(input)
        assertEquals(12.0, results[0])
    }

    //    TEST
//    a = 5
//    b = 5
//    println(a + b)
    @Test
    fun test002_assignVariablesNotInitializedAndTryToPrint() {
        val input = ASTNodeImpl("Program", null, ProgramNode, input_002)
        val interpreter = Interpreter()
        assertThrows<Exception>("Variable a not found") {
            interpreter.interpret(input)
        }
    }

    //    TEST
//    let a: number = 5
//    let b: number = 5
//    println(a + b)
    @Test
    fun test003_assignVariablesAndPrint() {
        val input = ASTNodeImpl("Program", null, ProgramNode, input_003)
        val interpreter = Interpreter()
        val results = interpreter.interpret(input)
        assertEquals(10.0, results[0])
    }

    //    TEST
//    let a: number = 5
//    let a: number = 5
    @Test
    fun test004_redeclareVariable() {
        val input = ASTNodeImpl("Program", null, ProgramNode, input_004)
        val interpreter = Interpreter()
        assertThrows<Exception>("Variable a already declared") {
            interpreter.interpret(input)
        }
    }

    //    TEST
//    let a:string = "hola"
//    a = "loco"
//    println(a)
    @Test
    fun test005_reassignVariableAndPrintLn() {
        val input = ASTNodeImpl("Program", null, ProgramNode, input_005)
        val interpreter = Interpreter()
        val results = interpreter.interpret(input)
        assertEquals("loco", results[0])
    }

    //    TEST
//    let a: string = "hola"
//    let b: string = "loco"
//    println(a + b)
    @Test
    fun test006_sumStringsAndPrint() {
        val input = ASTNodeImpl("Program", null, ProgramNode, input_006)
        val interpreter = Interpreter()
        val results = interpreter.interpret(input)
        assertEquals("holaloco", results[0])
    }
}
