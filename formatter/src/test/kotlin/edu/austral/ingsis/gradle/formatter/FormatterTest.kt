package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FormatterTest {
    @Test
    fun test001_assignationDeclarationAndOperation() {
        val source = "src/test/resources/config.json"
        val rules = RuleParser().parseRulesFromFile(source)
        //    let a : number = 5;
        //    5 + 5;
        val node = ASTNodeImpl("Program", null, ProgramNode, input_008)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a : number = 5.0;" + "\n" + "5.0 + 5.0;"
        assertEquals(expected, formattedCode)
    }

    @Test
    fun test002_assignationDeclarationAndPrintLn() {
        val source = "src/test/resources/config.json"
        val rules = RuleParser().parseRulesFromFile(source)
        //    let a: string = "hola"
        //    let b: string = "loco"
        //
        //    println (a+b)
        val node = ASTNodeImpl("Program", null, ProgramNode, input_012)
        val formatter = Formatter()
        val formattedCode = formatter.format(node, rules)
        val expected = "let a : string = \"hola\";" + "\n" + "let b : string = \"loco\";" + "\n\n" + "println(a + b);"
        assertEquals(expected, formattedCode)
    }
}
