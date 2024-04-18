import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.input_001
import edu.austral.ingsis.gradle.parser.input_002
import edu.austral.ingsis.gradle.parser.input_003
import edu.austral.ingsis.gradle.parser.input_004
import edu.austral.ingsis.gradle.parser.input_005
import edu.austral.ingsis.gradle.parser.input_006
import edu.austral.ingsis.gradle.parser.input_007
import edu.austral.ingsis.gradle.parser.input_008
import edu.austral.ingsis.gradle.parser.input_009
import edu.austral.ingsis.gradle.parser.input_010
import edu.austral.ingsis.gradle.parser.input_011
import edu.austral.ingsis.gradle.parser.input_012
import edu.austral.ingsis.gradle.parser.output_001
import edu.austral.ingsis.gradle.parser.output_002
import edu.austral.ingsis.gradle.parser.output_003
import edu.austral.ingsis.gradle.parser.output_004
import edu.austral.ingsis.gradle.parser.output_005
import edu.austral.ingsis.gradle.parser.output_006
import edu.austral.ingsis.gradle.parser.output_007
import edu.austral.ingsis.gradle.parser.output_008
import edu.austral.ingsis.gradle.parser.output_009
import edu.austral.ingsis.gradle.parser.output_010
import edu.austral.ingsis.gradle.parser.output_011
import edu.austral.ingsis.gradle.parser.output_012
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserTest {
    private val parser = createComposeParser()

    //    test 5
    @Test
    fun test001_parseSingleNumber() {
        val actual = parser.parse(InputContext(input_001)).first
        assertEquals(output_001, actual)
    }

    //    test 5 + 5
    @Test
    fun test002_parseSingleOperation() {
        val actual = parser.parse(InputContext(input_002)).first
        assertEquals(output_002, actual)
    }

    //    test "hola" + "loco"
    @Test
    fun test003_parseSingleStringOperator() {
        val actual = parser.parse(InputContext(input_003)).first
        assertEquals(output_003, actual)
    }

    //    test 5 * 5
    @Test
    fun test004_parseSingleMultiplicativeOperation() {
        val actual = parser.parse(InputContext(input_004)).first
        assertEquals(output_004, actual)
    }

    //    test 5 / 5
    @Test
    fun test005_parseSingleDivisionOperation() {
        val actual = parser.parse(InputContext(input_005)).first
        assertEquals(output_005, actual)
    }

    //    test let a : number ;
    @Test
    fun test006_parseVariableDeclaration() {
        val actual = parser.parse(InputContext(input_006)).first
        assertEquals(output_006, actual)
    }

    //    test let a : number = 5 ;
    @Test
    fun test007_parseExpression() {
        val actual = parser.parse(InputContext(input_007)).first
        assertEquals(output_007, actual)
    }

    //    test
//    let a : number = 5 ;
    @Test
    fun test008_parseExpressionWithOperationOnOtherLine() {
        val actual = parser.parse(InputContext(input_008)).first
        assertEquals(output_008, actual)
    }

    //    //    test println( 5 + 7 )
    @Test
    fun test009_parseExpressionWithPrintLnAndOperatorInside() {
        val actual = parser.parse(InputContext(input_009)).first
        assertEquals(output_009, actual)
    }

    //    //    test  (5 + 7)  *4
    @Test
    fun test010_parseExpressionWithSimpleOperator() {
        val actual = parser.parse(InputContext(input_010)).first
        assertEquals(output_010, actual)
    }

    //   readInput("hola lucho")
    @Test
    fun test011_parseReadInput() {
        val actual = parser.parse(InputContext(input_011)).first
        assertEquals(output_011, actual)
    }

    @Test
    fun test012_parseReadInput() {
        val actual = parser.parse(InputContext(input_012)).first
        assertEquals(output_012, actual)
    }

//    //    test
//    //    let a: number = 5
//    //    let b: number = 5
// //    println (a+b)
//    @Test
//    fun test011_parseExpressionWithPrintLnAndVariable() {
//        val actual = parser.parse(InputContext(input_011, 0)).first
//        val expected = ASTNodeImpl("Program", null, ProgramNode, output_011)
//        assertEquals(expected, actual)
//    }
//
//    //    test
//    //    let a: string = "hola"
//    //    let b: string = "loco"
//    //    println (a+b)
//    @Test
//    fun test012_parseExpressionWithPrintLnAndString() {
//        val actual = parser.parse(InputContext(input_012, 0)).first
//        val expected = ASTNodeImpl("Program", null, ProgramNode, output_012)
//        assertEquals(expected, actual)
//    }
//
//    //    test
// //    let a: string = "hola"
// //    a = "loco"
// //    println (a)
//    @Test
//    fun test013_parseExpressionWithPrintLnAndString() {
//        val actual = parser.parse(InputContext(input_013, 0)).first
//        val expected = ASTNodeImpl("Program", null, ProgramNode, output_013)
//        assertEquals(expected, actual)
//    }
}
