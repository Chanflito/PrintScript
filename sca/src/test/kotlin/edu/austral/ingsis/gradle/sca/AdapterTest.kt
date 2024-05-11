package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.sca.adapter.FileToJsonAdapter
import edu.austral.ingsis.gradle.sca.adapter.json.JsonReadInputAdapter
import edu.austral.ingsis.gradle.sca.analyzer.DefaultAnalyzer
import edu.austral.ingsis.gradle.sca.rule.ComposeRule
import edu.austral.ingsis.gradle.sca.rule.DisabledRule
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.lang.Exception
import kotlin.test.assertEquals

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
        assert(rule is ComposeRule)
        val composeRule = rule as ComposeRule
        val node = input_002
        val result = composeRule.verify(node)
        val analyzer = DefaultAnalyzer().analyze(node, composeRule.getRules())
        assert(result is ReportSuccess)
        assertEquals(result, analyzer)
    }

    @Test
    fun test005_adapterTestWithPrintLnRuleEnabledAndCamelCaseRuleEnabledAndReadInputDisabled() {
        val source = File("src/test/resources/config_001.json")
        val fileContent = source.readText()
        val jsonObject: JsonObject = Json.parseToJsonElement(fileContent).jsonObject
        val adapter = JsonReadInputAdapter()
        val rule = adapter.adapt(jsonObject)
        assert(rule is DisabledRule)
    }
}
