package parser

import common.ast.*
import common.token.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import parser.impl.ParserImpl

class ParserTest {
    val parser = ParserImpl()
    @Test
//    test 5
    fun test001_parseSingleNumber() {
        val ast = parser.parse(input_001)
        assert(ast.contains(output_001))
    }

    @Test
//    test 5 + 5
    fun test002_parseSingleOperation() {
        val ast = parser.parse(input_002)
        assert(ast.contains(output_002))
    }

    @Test
//    test "hola" + "loco"
    fun test003_parseSingleStringOperator() {
        val ast = parser.parse(input_003)
        assert(ast.contains(output_003))
    }

    @Test
//    test 5 * 5
    fun test004_parseSingleMultiplicativeOperation() {
        val ast = parser.parse(input_004)
        assert(ast.contains(output_004))
    }

    @Test
//    test 5 / 5
    fun test005_parseSingleDivisionOperation() {

        val ast = parser.parse(input_005)
        assert(ast.contains(output_005))
    }

    @Test
//    test let a : number ;
    fun test006_parseVariableDeclaration() {
        val ast = parser.parse(input_006)
        assert(ast.contains(output_006))
    }

    @Test
//    test let a : number = 5 ;
    fun test007_parseExpression() {
        val ast = parser.parse(input_007)
        assert(ast.contains(output_007))
    }

    @Test
//    test
//    let a : number = 5 ;
//    5 + 5;
    fun test008_parseExpressionWithOperationOnOtherLine() {
        val ast = parser.parse(input_008)
        assertEquals(output_008, ast)
    }

    @Test
//    test println( 5 + 7 )
    fun test009_parseExpressionWithPrintLnAndOperatorInside(){
        val ast= parser.parse(input_009);
        assertEquals(output_009, ast)
    }

    @Test
    //    test  (5 + 7)  *4
    fun test010_parseExpressionWithSimpleOperator(){
        val ast= parser.parse(input_010);
        assertEquals(output_010, ast)
    }

    @Test
    //    test
    //    let a: number = 5
    //    let b: number = 5
//    println (a+b)
    fun test011_parseExpressionWithPrintLnAndVariable(){
        val ast= parser.parse(input_011);
        assertEquals(output_011, ast)
    }


    @Test
    //    test
    //    let a: string = "hola"
    //    let b: string = "loco"
    //    println (a+b)
    fun test012_parseExpressionWithPrintLnAndString(){
        val ast= parser.parse(input_012);
        assertEquals(output_012, ast)
    }

    @Test
//    test
//    let a: string = "hola"
//    a = "loco"
//    println (a)
    fun test013_parseExpressionWithPrintLnAndString(){
        val ast= parser.parse(input_013);
        assertEquals(output_013, ast)
    }
}


