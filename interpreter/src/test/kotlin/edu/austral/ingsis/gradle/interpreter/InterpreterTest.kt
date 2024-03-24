package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.ProgramNode
import common.ast.ASTNodeImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterTest {
    @Test
//    TEST
//    println(5 + 7)
    fun test001_directSumAndPrint() {
        val input = ASTNodeImpl("Program",null, ProgramNode, input_001)
        val interpreter = Interpreter()
        val results= interpreter.interpret(input)
        assertEquals(12.0, results[0])
    }
    @Test
//    TEST
//    a = 5
//    b = 5
//    println(a + b)
    fun test002_assignVariablesNotInitializedAndTryToPrint(){
        val input = ASTNodeImpl("Program",null, ProgramNode, input_002)
        val interpreter = Interpreter()
        assertThrows<Exception>("Variable a not found"){
            interpreter.interpret(input)
        }
    }

    @Test
//    TEST
//    let a: number = 5
//    let b: number = 5
//    println(a + b)
    fun test003_assignVariablesAndPrint(){
        val input = ASTNodeImpl("Program",null, ProgramNode, input_003)
        val interpreter = Interpreter()
        val results= interpreter.interpret(input)
        assertEquals(10.0, results[0])
    }

    @Test
//    TEST
//    let a: number = 5
//    let a: number = 5
    fun test004_redeclareVariable(){
        val input = ASTNodeImpl("Program",null, ProgramNode, input_004)
        val interpreter = Interpreter()
        assertThrows<Exception>("Variable a already declared"){
            interpreter.interpret(input)
        }

    }

    @Test
//    TEST
//    let a:string = "hola"
//    a = "loco"
//    println(a)
    fun test005_reassignVariableAndPrintLn(){
        val input = ASTNodeImpl("Program",null, ProgramNode, input_005)
        val interpreter = Interpreter()
        val results= interpreter.interpret(input)
        assertEquals("loco", results[0] )
    }

    @Test
//    TEST
//    let a: string = "hola"
//    let b: string = "loco"
//    println(a + b)
    fun test006_sumStringsAndPrint(){
        val input = ASTNodeImpl("Program",null, ProgramNode, input_006)
        val interpreter = Interpreter()
        val results= interpreter.interpret(input)
        assertEquals("holaloco", results[0] )
    }

}