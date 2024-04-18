package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.formatter.rule.Rule
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleAdapter
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleParser
import edu.austral.ingsis.gradle.formatter.rule.adapter.context.Default
import edu.austral.ingsis.gradle.formatter.rule.adapter.context.IfBlock

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
    val defaultRuleData = RuleParser().parseRulesFromFile(rulesFile, Default())
    val adaptedRules = defaultRuleData.map { RuleAdapter().adapt(it) }
    return adaptedRules
}

fun createIfBlockRules(rulesFile: String): List<Rule> {
    val ifBlockRuleData = RuleParser().parseRulesFromFile(rulesFile, IfBlock())
    val adaptedRules = ifBlockRuleData.map { RuleAdapter().adapt(it) }
    return adaptedRules
}
