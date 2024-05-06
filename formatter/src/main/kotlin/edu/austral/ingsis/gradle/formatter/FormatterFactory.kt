package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.formatter.rule.Rule
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleAdapter
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleParser
import edu.austral.ingsis.gradle.formatter.rule.adapter.context.Block
import edu.austral.ingsis.gradle.formatter.rule.adapter.context.Default

fun createDefaultFormatter(): Formatter<AST> {
    return ComposeFormatter(
        listOf(
            BlockFormatter(),
            DeclarationAssignationFormatter(),
            DeclarationFormatter(),
            SumFormatter(),
            SubtractFormatter(),
            MultiplyFormatter(),
            DivideFormatter(),
            StringFormatter(),
            NumberFormatter(),
            BooleanFormatter(),
            IdentifierFormatter(),
            IfElseStatementFormatter(),
            IfStatementFormatter(),
            PrintLnFormatter(),
            ReadEnvFormatter(),
            ReadInputFormatter(),
            ProgramNodeFormatter(),
            ReAssignationFormatter(),
        ),
    )
}

fun createDefaultRules(rulesFile: String): List<Rule> {
    val defaultRuleData = RuleParser().parseRulesFromFile(rulesFile, Default())
    val adaptedRules = defaultRuleData.map { RuleAdapter().adapt(it) }
    return adaptedRules
}

fun createBlockRules(rulesFile: String): List<Rule> {
    val blockRuleData = RuleParser().parseRulesFromFile(rulesFile, Block())
    val adaptedRules = blockRuleData.map { RuleAdapter().adapt(it) }
    return adaptedRules
}
