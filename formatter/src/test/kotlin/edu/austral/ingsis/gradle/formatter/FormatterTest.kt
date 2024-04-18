package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FormatterTest {
    @Test
    fun test001_formatDeclarationAssignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input001
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "let aBlue : String = \"blue\";", actual = formatted)
    }

    @Test
    fun test002_formatIfStatement() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input002
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "if (a) {\n   println(a + b);\n   let aBlue : String = \"blue\";\n}", actual = formatted)
    }

    @Test
    fun test003_formatIfElseStatement() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input003
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "if (a) {\n   println(a + b);\n}else {\n   println(a - b);\n}", actual = formatted)
    }

    @Test
    fun test004_formatReAssignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input004
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "a = 1;", actual = formatted)
    }

    @Test
    fun test005_formatDeclarationAssignation_ReAssignation_DeclarationAssignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input005
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "let aBlue : String = \"blue\";\na = 1;\nlet aBlue : String = \"blue\";", actual = formatted)
    }

    @Test
    fun test006_testPrintLn_ReadInput() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input006
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "println(a + b);\nreadInput(Introduzca un valor);", actual = formatted)
    }
}
