package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.cli.adapter.FileAdapter
import edu.austral.ingsis.gradle.sca.ReportSuccess
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.Ignore
import kotlin.test.assertEquals

class CliTest {
    // TODO: Remove this tests in the future.
    @Test
    @Ignore
    fun test001_executeFunctionWithValidInputShouldReturnList() {
        val source = "src/test/resources/code/input/source_001"
        val content = FileAdapter().adapt(File(source))
        val result = ExecuteCliFunction().evaluate(content)
        assert(result.isNotEmpty())
        assertEquals(expected = 3.0, actual = result[0])
    }

    @Test
    @Ignore
    fun test002_analyzeFunctionWithValidInputShouldReturnReportResult() {
        val source = "src/test/resources/code/input/source_002"
        val config = "src/test/resources/rule/sca/config_001.json"
        val sourceFile = FileAdapter().adapt(File(source))
        val result = AnalyzeCliFunction().evaluate(Pair(sourceFile, File(config)))
        assertEquals(expected = ReportSuccess.toString(), actual = result.toString())
    }

    @Test
    @Ignore
    fun test003_formatFunctionWithValidInputShouldReturnFormattedCode() {
        val source = "src/test/resources/code/input/source_format_001"
        val config = "src/test/resources/rule/formatter/config_001.json"
        val sourceFile = FileAdapter().adapt(File(source))
        val expected =
            "let aCa : string = \"Hello\";\n" +
                "\n" +
                "println(aCa);\n" +
                "let bCa : number = 10.0;\n" +
                "\n" +
                "println(bCa);"
        val result = FormatCliFunction().evaluate(Pair(sourceFile, File(config)))
        assertEquals(expected = expected, actual = result)
    }
}
