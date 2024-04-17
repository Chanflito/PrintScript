package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.formatter.rule.Rule
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleAdapter
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleParser

fun createDefaultFormatter(): Formatter<AST> {
    return ComposeFormatter(
        listOf(
            DeclarationAssignationFormatter(),
            ReAssignationFormatter(),
            IfStatementFormatter(),
            IfElseStatementFormatter(),
            PrintLnFormatter(),
            ReadInputFormatter(),
        ),
    )
}

fun createDefaultRules(rulesFile: String): List<Rule> {
    val ruleData = RuleParser().parseRulesFromFile(rulesFile)
    val adaptedRules = ruleData.map { RuleAdapter().adapt(it) }
    return adaptedRules
}
