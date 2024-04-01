package edu.austral.ingsis.gradle.formatter.rule.adapter

import edu.austral.ingsis.gradle.formatter.rule.NewLineBeforePrintlnRule
import edu.austral.ingsis.gradle.formatter.rule.Rule
import edu.austral.ingsis.gradle.formatter.rule.SpaceAfterColonRule
import edu.austral.ingsis.gradle.formatter.rule.SpaceAroundEqualRule
import edu.austral.ingsis.gradle.formatter.rule.SpaceBeforeColonRule

class RuleAdapter {
    fun adapt(ruleData: RuleData): Rule {
        return when (ruleData.type) {
            "spaceBeforeColon" -> SpaceBeforeColonRule()
            "spaceAfterColon" -> SpaceAfterColonRule()
            "spaceAroundEqual" -> SpaceAroundEqualRule()
            "newlineBeforePrintln" -> NewLineBeforePrintlnRule(ruleData.maxInt)
            else -> throw IllegalArgumentException("Unknown rule type: ${ruleData.type}")
        }
    }
}
