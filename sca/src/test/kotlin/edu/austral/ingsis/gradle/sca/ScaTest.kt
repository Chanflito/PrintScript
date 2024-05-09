package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.sca.analyzer.DefaultAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.FunctionAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.ProgramNodeAnalyzer
import edu.austral.ingsis.gradle.sca.analyzer.StatementAnalyzer
import edu.austral.ingsis.gradle.sca.rule.IdentifierRule
import edu.austral.ingsis.gradle.sca.rule.PrintlnRule
import edu.austral.ingsis.gradle.sca.rule.ReadInputRule
import edu.austral.ingsis.gradle.sca.util.CamelCaseRule
import edu.austral.ingsis.gradle.sca.util.SnakeCaseRule
import edu.austral.ingsis.gradle.sca.util.identifierRuleWithCustomErrorMap
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScaTest {
    @Test // let aBlue : String = "blue";
    fun test001_camelCaseRuleWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = input_001
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assertEquals(ReportSuccess, result)
    }

    @Test // let a_Blue : String = "blue";
    fun test002_camelCaseRuleWithNoValidInputShouldReturnFailure() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = input_002
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportFailure)
    }

    @Test // let a_blue : String = "blue";
    fun test003_snakeCaseRuleWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val node = input_003
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportSuccess)
    }

    @Test // let aBlue : String = "blue";
    fun test004_snakeCaseRuleWithNoValidInputShouldReturnFailure() {
        val rule = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val node = input_001
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportFailure)
    }

    @Test // println(b);
    fun test005_printlnRuleWithValidInputShouldReturnSuccess() {
        val rule = PrintlnRule()
        val node = input_004
        val result = FunctionAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportSuccess)
    }

    @Test // println(a+b);
    fun test006_printlnRuleWithValidInputShouldReturnSuccess() {
        val rule = PrintlnRule()
        val node = input_005
        val result = FunctionAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportFailure)
    }

    @Test // let a_snake : String ="snake"; println(a_snake);
    fun test007_composeRuleWithValidInputShouldReturnSuccess() {
        val rule1 = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val rule2 = PrintlnRule()
        val rules = listOf(rule1, rule2)
        val node = input_006
        val result = DefaultAnalyzer().analyze(node, rules)
        assert(result is ReportSuccess)
    }

    @Test // let aCamel : string;
    fun test008_camelCaseRuleWithNoAssignationWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = input_007
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportSuccess)
    }

    @Test // let a_snake: string;
    fun test009_snakeCaseRuleWithNoAssignationWithValidInputShouldReturnSuccess() {
        val rule = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val node = input_008
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportSuccess)
    }

    @Test // let a_snake : string;
    fun test010_camelCaseRuleWithNoAssignationWithInvalidInputShouldReturnFailure() {
        val rule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val node = input_008
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportFailure)
    }

    @Test // println(readInput("SOME_INPUT"))
    fun test011_enforceLiteralOrIdentifierInPrintlnWithReadInputShouldReturnFailure() {
        val rule = PrintlnRule()
        val node = input_009
        val result = FunctionAnalyzer().analyze(node, listOf(rule))
        assert((result as ReportFailure).failureMessages.size == 1)
    }

    @Test // println(readEnv("SOME_ENV"))
    fun test011_enforceLiteralOrIdentifierInPrintlnWithReadEnvShouldReturnFailure() {
        val rule = PrintlnRule()
        val node = input_010
        val result = FunctionAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportFailure)
        assert((result as ReportFailure).failureMessages.size == 1)
    }

    @Test // if (a) {println(a+b); else {println(a-b}}
    fun test012_enforceLiteralOrIdentifierInPrintlnWithIfElseAndExpressionShouldReturnFailure() {
        val rule = PrintlnRule()
        val node = input_011
        val result = DefaultAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportFailure)
        assert((result as ReportFailure).failureMessages.size == 2)
    }

    @Test // if (aCamel) {println(aCamel); else {println(aCamel)}
    fun test013_composeRuleWithCamelCaseRuleAndEnforceLiteralOrIdentifierInPrintln() {
        val rule1 = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val rule2 = PrintlnRule()
        val rules = listOf(rule1, rule2)
        val node = input_012
        val result = DefaultAnalyzer().analyze(node, rules)
        assert(result is ReportSuccess)
    }

    @Test // if (a_snake) {println(a_snake); else {println(a_snake)}
    fun test014_composeRuleWithCamelCaseRuleAndEnforceLiteralOrIdentifierInPrintln() {
        val rule1 = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val rule2 = PrintlnRule()
        val rules = listOf(rule1, rule2)
        val node = input_013
        val result = DefaultAnalyzer().analyze(node, rules)
        assert(result is ReportFailure)
        assert((result as ReportFailure).failureMessages.size == 3)
    }

    @Test // if (aCamel) {println(aCamel); else {println(aCamel)}
    fun test015_composeRuleWithSnakeCaseAndEnforceLiteralOrIdentifierInPrintln() {
        val rule1 = IdentifierRule(SnakeCaseRule, identifierRuleWithCustomErrorMap)
        val rule2 = PrintlnRule()
        val rules = listOf(rule1, rule2)
        val node = input_012
        val result = DefaultAnalyzer().analyze(node, rules)
        assert(result is ReportFailure)
        assert((result as ReportFailure).failureMessages.size == 3)
    }

    @Test // if (aCamel) {println(aCamel + aCamelCase); else {println(aCamel + aCamelCase)}
    fun test016_composeRuleWithCamelCaseAndEnforceLiteralOrIdentifierInPrintln() {
        val rule1 = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val rule2 = PrintlnRule()
        val rules = listOf(rule1, rule2)
        val node = input_014
        val result = DefaultAnalyzer().analyze(node, rules)
        assert(result is ReportFailure)
        assert((result as ReportFailure).failureMessages.size == 2)
    }

    @Test // let a : number = readInput (5+5)
    fun test017_enforceLiteralOrIdentifierInReadInputWithExpressionShouldReportFailure() {
        val rule = ReadInputRule()
        val node = input_015
        val result = StatementAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportFailure)
        assert((result as ReportFailure).failureMessages.size == 1)
    }

    @Test // readInput("SOME_INPUT")
    fun test018_enforceLiteralOrIdentifierInReadInputWithLiteralShouldReportSuccess() {
        val rule = ReadInputRule()
        val node = input_016
        val result = FunctionAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportSuccess)
    }

    @Test
    fun test019_enforceLiteralOrIdentifierInReadInputWithLiteralOnIfStatementShouldReportSuccess() {
        val rule = ReadInputRule()
        val node = input_017
        val result = ProgramNodeAnalyzer().analyze(node, listOf(rule))
        assert(result is ReportSuccess)
    }

    // let a_snake: string = "some string"
    // let aCamel: string = "some string"
    // if (a_snake) {readInput(a_snake); else {println(a_snake) readEnv("ENV") readInput (aCamel + a_snake)}
    @Test
    fun test020_composeRuleWithAllNewStatementsAndAllRulesActivatedWithCamelCase() {
        val readInputRule = ReadInputRule()
        val printlnRule = PrintlnRule()
        val camelCaseRule = IdentifierRule(CamelCaseRule, identifierRuleWithCustomErrorMap)
        val rules = listOf(readInputRule, printlnRule, camelCaseRule)
        val node = input_018
        val result = DefaultAnalyzer().analyze(node, rules)
        assert(result is ReportFailure)
        assert((result as ReportFailure).failureMessages.size == 6)
    }
}
