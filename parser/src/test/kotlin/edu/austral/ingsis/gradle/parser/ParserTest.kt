import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.impl.ProgramNodeParser
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
import edu.austral.ingsis.gradle.parser.input_013
import edu.austral.ingsis.gradle.parser.input_014
import edu.austral.ingsis.gradle.parser.input_015
import edu.austral.ingsis.gradle.parser.input_016
import edu.austral.ingsis.gradle.parser.input_017
import edu.austral.ingsis.gradle.parser.input_018
import edu.austral.ingsis.gradle.parser.input_019
import edu.austral.ingsis.gradle.parser.input_020
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
import edu.austral.ingsis.gradle.parser.output_013
import edu.austral.ingsis.gradle.parser.output_014
import edu.austral.ingsis.gradle.parser.output_015
import edu.austral.ingsis.gradle.parser.output_016
import edu.austral.ingsis.gradle.parser.output_017
import edu.austral.ingsis.gradle.parser.output_018
import edu.austral.ingsis.gradle.parser.output_019
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

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

    @Test
    fun test013_parseReadEnv() {
        val actual = parser.parse(InputContext(input_013)).first
        assertEquals(output_013, actual)
    }

    @Test
    fun test014_parseIfStatement() {
        val actual = parser.parse(InputContext(input_014)).first
        assertEquals(output_014, actual)
    }

    @Test
    fun test015_parseIfElseStatement() {
        val actual = parser.parse(InputContext(input_015)).first
        assertEquals(output_015, actual)
    }

    @Test
    fun test016_programNodeParser() {
        val actual = ProgramNodeParser().parse(InputContext(input_016)).first
        assertEquals(output_016, actual)
    }

    @Test
    fun test017_parseMultiLineInput() {
        val actual = ProgramNodeParser().parse(InputContext(input_017)).first
        assertEquals(output_017, actual)
    }

    @Test
    fun test_018_constAssignationDeclaration() {
        val actual = parser.parse(InputContext(input_018)).first

        assertEquals(output_018, actual)
    }

    @Test
    fun test_019_complexOperation() {
        val actual = parser.parse(InputContext(input_019)).first
        assertEquals(output_019, actual)
    }

    @Test
    fun test_020_stringMultiplicationShouldFail() {
        assertFailsWith<Exception>("Cannot perform multiplication operation over strings") {
            parser.parse(
                InputContext(input_020),
            )
        }
    }
}
