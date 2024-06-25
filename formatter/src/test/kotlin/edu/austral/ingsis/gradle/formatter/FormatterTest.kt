package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rules
import input001
import input002
import input003
import input004
import input005
import input006
import input007
import input008
import input009
import input009_reversed
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class FormatterTest {
    @Test
    fun test001_formatDeclarationAssignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input001
        val expected = File("src/test/resources/output/output001").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test002_formatIfStatement() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input002
        val expected = File("src/test/resources/output/output002").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test003_formatIfElseStatement() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input003
        val expected = File("src/test/resources/output/output003").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test004_formatReAssignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input004
        val expected = File("src/test/resources/output/output004").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test005_formatDeclarationAssignation_ReAssignation_DeclarationAssignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input005
        val expected = File("src/test/resources/output/output005").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test006_testPrintLn_ReadInput() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input006
        val expected = File("src/test/resources/output/output006").readText()
        println(expected)
        val formatted = formatter.format(ast, rules)
        println(formatted)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test007_test_readEnv() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input007
        val expected = File("src/test/resources/output/output007").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test008_test_Declaration_Assignation_Boolean_Type() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input008
        val expected = File("src/test/resources/output/output008").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test009_test_readEnv_Declaration_Assignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input009
        val expected = File("src/test/resources/output/output009").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }

    @Test
    fun test010_test_Declaration_Assignation_readEnv() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config.json"))
        val ifBlockRules = ComposeRule(createBlockRules("src/test/resources/config.json"))
        val rules = Rules(defaultRules, ifBlockRules)
        val ast = input009_reversed
        val expected = File("src/test/resources/output/output009-reversed").readText()
        val formatted = formatter.format(ast, rules)
        assertEquals(expected = expected, actual = formatted)
    }
}
