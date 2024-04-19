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
        assertEquals(expected = "let aBlue : string = blue;", actual = formatted)
    }

    @Test
    fun test002_formatIfStatement() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input002
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "if (a) {\n   println(a + b);\n   let aBlue : string = blue;\n}", actual = formatted)
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
        assertEquals(expected = "let aBlue : string = blue;\na = 1;\nlet aBlue : string = blue;", actual = formatted)
    }

    @Test
    fun test006_testPrintLn_ReadInput() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input006
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "println(a + b);\nreadInput(\"Introduzca un valor\");", actual = formatted)
    }

    @Test
    fun test007_test_readEnv() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input007
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "let input : string = readEnv(\"PATH\");", actual = formatted)
    }

    @Test
    fun test008_test_Declaration_Assignation_Boolean_Type() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input008
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "let b : boolean = true;", actual = formatted)
    }

    @Test
    fun test009_test_readEnv_Declaration_Assignation() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input009
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "let a : string = readEnv(\"PATH\");\nlet b : boolean = true;", actual = formatted)
    }

    @Test
    fun test009_test_Declaration_Assignation_readEnv() {
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
        val ifBlockRules = ComposeRule(createIfBlockRules("src/test/resources/config_001.json"))
        val ast = input009_reversed
        val formatted = formatter.format(ast, defaultRules, ifBlockRules)
        assertEquals(expected = "let b : boolean = true;\nlet a : string = readEnv(\"PATH\");", actual = formatted)
    }
}
