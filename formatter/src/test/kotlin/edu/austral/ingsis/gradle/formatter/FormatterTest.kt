package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FormatterTest {
    @Test
    fun test001_assignationDeclarationAndOperation() {
        val source = "src/test/resources/config_001.json"
        val rules = RuleParser().parseRulesFromFile(source)
        //    let a : number = 5;
        //    5 + 5;
        val node = ASTNodeImpl("Program", null, ProgramNode, input_001)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a : number = 5.0;" + "\n" + "5.0 + 5.0;"
        assertEquals(expected = expected, actual = formattedCode)
    }

    @Test
    fun test002_assignationDeclarationAndPrintLn() {
        val source = "src/test/resources/config_001.json"
        val rules = RuleParser().parseRulesFromFile(source)
        //    let a: string = "hola"
        //    let b: string = "loco"
        //
        //    println (a+b)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_002)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a : string = \"hola\";" + "\n" + "let b : string = \"loco\";" + "\n\n" + "println(a + b);"
        assertEquals(expected = expected, actual = formattedCode)
    }

    @Test
    fun test003_assignationDeclarationWithSpaceBeforeColonDisable() {
        val source = "src/test/resources/config_002.json"
        val rules = RuleParser().parseRulesFromFile(source)
        // let a : number = 5;
        val node = ASTNodeImpl("Program", token = null, ProgramNode, input_003)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a: number = 5.0;"
        assertEquals(expected = expected, actual = formattedCode)
    }

    @Test
    fun test004_assignationDeclarationWithSpaceAfterColonDisable() {
        val source = "src/test/resources/config_003.json"
        val rules = RuleParser().parseRulesFromFile(source)
        // let a : string = "hello";
        val node = ASTNodeImpl("Program", token = null, ProgramNode, input_004)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a :string = \"hello\";"
        assertEquals(expected = expected, actual = formattedCode)
    }

    @Test
    fun test005_assignationDeclarationWithSpaceBeforeColonAndSpaceAfterColonDisable() {
        val source = "src/test/resources/config_004.json"
        val rules = RuleParser().parseRulesFromFile(source)
        // let a : string = "hello";
        val node = ASTNodeImpl("Program", token = null, ProgramNode, input_004)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a:string = \"hello\";"
        assertEquals(expected = expected, actual = formattedCode)
    }

    @Test
    fun test006_assignationDeclarationWithSpaceAroundEqualDisable() {
        val source = "src/test/resources/config_005.json"
        val rules = RuleParser().parseRulesFromFile(source)
        // let a : string = "hello";
        val node = ASTNodeImpl("Program", token = null, ProgramNode, input_004)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a : string=\"hello\";"
        assertEquals(expected = expected, actual = formattedCode)
    }

    @Test
    fun test007_assignationDeclarationWithAllRulesDisable() {
        val source = "src/test/resources/config_006.json"
        val rules = RuleParser().parseRulesFromFile(source)
        // let a : string = "hello";
        val node = ASTNodeImpl("Program", token = null, ProgramNode, input_004)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a:string=\"hello\";"
        assertEquals(expected = expected, actual = formattedCode)
    }
}
