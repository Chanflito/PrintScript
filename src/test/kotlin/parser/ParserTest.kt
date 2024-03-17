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

}


