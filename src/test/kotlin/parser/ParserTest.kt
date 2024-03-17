package parser

import common.ast.*
import common.token.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import parser.impl.ParserImpl

class ParserTest {
    private val parser = ParserImpl()
    @Test
//    test 5
    fun test001_parseSingleNumber() {
        val actual = parser.parse(input_001)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_001)
        assertEquals(expected,actual)
    }

    @Test
//    test 5 + 5
    fun test002_parseSingleOperation() {
        val actual = parser.parse(input_002)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_002)
        assertEquals(expected,actual)
    }

    @Test
//    test "hola" + "loco"
    fun test003_parseSingleStringOperator() {
        val actual = parser.parse(input_003)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_003)
        assertEquals(expected,actual)
    }

    @Test
//    test 5 * 5
    fun test004_parseSingleMultiplicativeOperation() {
        val actual = parser.parse(input_004)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_004)
        assertEquals(expected,actual)
    }

    @Test
//    test 5 / 5
    fun test005_parseSingleDivisionOperation() {
        val actual = parser.parse(input_005)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_005)
        assertEquals(expected,actual)
    }

    @Test
//    test let a : number ;
    fun test006_parseVariableDeclaration() {
        val actual = parser.parse(input_006)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_006)
        assertEquals(expected,actual)
    }

    @Test
//    test let a : number = 5 ;
    fun test007_parseExpression() {
        val actual = parser.parse(input_007)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_007)
        assertEquals(expected,actual)
    }

    @Test
//    test
//    let a : number = 5 ;
//    5 + 5;
    fun test008_parseExpressionWithOperationOnOtherLine() {
        val actual = parser.parse(input_008)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_008)
        assertEquals(expected, actual)
    }

    @Test
//    test println( 5 + 7 )
    fun test009_parseExpressionWithPrintLnAndOperatorInside(){
        val actual= parser.parse(input_009);
        val expected= ASTNodeImpl("Program", null, ProgramNode, output_009)
        assertEquals(expected, actual)
    }

    @Test
    //    test  (5 + 7)  *4
    fun test010_parseExpressionWithSimpleOperator(){
        val actual= parser.parse(input_010);
        val expected= ASTNodeImpl("Program", null, ProgramNode, output_010)
        assertEquals(expected, actual)
    }

    @Test
    //    test
    //    let a: number = 5
    //    let b: number = 5
//    println (a+b)
    fun test011_parseExpressionWithPrintLnAndVariable(){
        val actual= parser.parse(input_011);
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_011)
        assertEquals(expected, actual)
    }


    @Test
    //    test
    //    let a: string = "hola"
    //    let b: string = "loco"
    //    println (a+b)
    fun test012_parseExpressionWithPrintLnAndString(){
        val actual= parser.parse(input_012);
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_012)
        assertEquals(expected, actual)
    }

    @Test
//    test
//    let a: string = "hola"
//    a = "loco"
//    println (a)
    fun test013_parseExpressionWithPrintLnAndString(){
        val actual= parser.parse(input_013);
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_013)
        assertEquals(expected, actual)
    }
}


