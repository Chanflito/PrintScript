package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.sca.adapter.FileToJsonAdapter
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.lang.Exception

class AdapterTest {
    @Test
    fun test001_adapterTestWithPrintLnRuleEnabledAndSnakeCaseRuleShouldFindFile() {
        val source = File("src/test/resources/config.json")
        val adapter = FileToJsonAdapter()
        assertDoesNotThrow("File does not exist") {
            adapter.adapt(source)
        }
    }

    @Test
    fun test002_adapterTestWithInvalidJsonFileShouldThrowError() {
        val source = File("src/test/resources/invalid-config.json")
        val adapter = FileToJsonAdapter()
        assertThrows<Exception> { adapter.adapt(source) }
    }

    @Test
    fun test003_adapterTestWithPrintLnRuleEnabledAndCamelCaseRuleShouldReturnComposeRule() {
        val source = File("src/test/resources/config.json")
        val adapter = FileToJsonAdapter()
        val rule = adapter.adapt(source)
        assert(rule is ComposeRule)
    }

    @Test
    fun test004_adapterTestWithPrintLnRuleEnabledAndCamelCaseRuleAndValidInputShouldReturnReportSuccess() {
        val source = File("src/test/resources/config.json")
        val adapter = FileToJsonAdapter()
        val rule = adapter.adapt(source)
        val node = input_002
        val result = rule.verify(node)
        assert(rule is ComposeRule)
        assert(result is ReportSuccess)
    }
}
