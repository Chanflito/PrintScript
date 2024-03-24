import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.*
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserTest {
    private val parser = createComposeParser()
    @Test
//    test 5
    fun test001_parseSingleNumber() {
        val actual = parser.parse(InputContext(input_001,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_001)
        assertEquals(expected,actual)
    }

    @Test
//    test 5 + 5
    fun test002_parseSingleOperation() {
        val actual = parser.parse(InputContext(input_002,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_002)
        assertEquals(expected,actual)
    }

    @Test
//    test "hola" + "loco"
    fun test003_parseSingleStringOperator() {
        val actual = parser.parse(InputContext(input_003,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_003)
        assertEquals(expected,actual)
    }

    @Test
//    test 5 * 5
    fun test004_parseSingleMultiplicativeOperation() {
        val actual = parser.parse(InputContext(input_004,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_004)
        assertEquals(expected,actual)
    }

    @Test
//    test 5 / 5
    fun test005_parseSingleDivisionOperation() {
        val actual = parser.parse(InputContext(input_005,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_005)
        assertEquals(expected,actual)
    }

    @Test
    //    test let a : number ;
    fun test006_parseVariableDeclaration() {
        val actual = parser.parse(InputContext(input_006,0)).first
        //val actual= DeclarationParser(0).parse(input_006)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_006)
        assertEquals(expected,actual)
    }

    @Test
//    test let a : number = 5 ;
    fun test007_parseExpression() {
        val actual = parser.parse(InputContext(input_007,0)).first
        ///val actual= DeclarationParser(0).parse(input_007)
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_007)
        assertEquals(expected,actual)
    }

    @Test
//    test
//    let a : number = 5 ;
//    5 + 5;
    fun test008_parseExpressionWithOperationOnOtherLine() {
        val actual = parser.parse(InputContext(input_008,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_008)
        assertEquals(expected, actual)
    }

    @Test
//    test println( 5 + 7 )
    fun test009_parseExpressionWithPrintLnAndOperatorInside(){
        val actual= parser.parse(InputContext(input_009,0)).first
        val expected= ASTNodeImpl("Program", null, ProgramNode, output_009)
        assertEquals(expected, actual)
    }

    @Test
    //    test  (5 + 7)  *4
    fun test010_parseExpressionWithSimpleOperator(){
        val actual= parser.parse(InputContext(input_010,0)).first
        val expected= ASTNodeImpl("Program", null, ProgramNode, output_010)
        assertEquals(expected, actual)
    }

    @Test
    //    test
    //    let a: number = 5
    //    let b: number = 5
//    println (a+b)
    fun test011_parseExpressionWithPrintLnAndVariable(){
        val actual= parser.parse(InputContext(input_011,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_011)
        assertEquals(expected, actual)
    }


    @Test
    //    test
    //    let a: string = "hola"
    //    let b: string = "loco"
    //    println (a+b)
    fun test012_parseExpressionWithPrintLnAndString(){
        val actual= parser.parse(InputContext(input_012,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_012)
        assertEquals(expected, actual)
    }

    @Test
//    test
//    let a: string = "hola"
//    a = "loco"
//    println (a)
    fun test013_parseExpressionWithPrintLnAndString(){
        val actual= parser.parse(InputContext(input_013,0)).first
        val expected = ASTNodeImpl("Program", null, ProgramNode, output_013)
        assertEquals(expected, actual)
    }
}


