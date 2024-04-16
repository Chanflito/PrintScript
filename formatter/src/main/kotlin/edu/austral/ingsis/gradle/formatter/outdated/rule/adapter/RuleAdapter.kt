package edu.austral.ingsis.gradle.formatter.outdated.rule.adapter

import edu.austral.ingsis.gradle.formatter.outdated.rule.DisabledRule
import edu.austral.ingsis.gradle.formatter.outdated.rule.NewLineBeforePrintlnRule
import edu.austral.ingsis.gradle.formatter.outdated.rule.Rule
import edu.austral.ingsis.gradle.formatter.outdated.rule.SpaceAfterColonRule
import edu.austral.ingsis.gradle.formatter.outdated.rule.SpaceAroundEqualRule
import edu.austral.ingsis.gradle.formatter.outdated.rule.SpaceBeforeColonRule

class RuleAdapter {
    fun adapt(ruleData: RuleData): Rule {
        if (ruleData.allowed) {
            return when (ruleData.type) {
                "spaceBeforeColon" -> SpaceBeforeColonRule()
                "spaceAfterColon" -> SpaceAfterColonRule()
                "spaceAroundEqual" -> SpaceAroundEqualRule()
                "newlineBeforePrintln" -> NewLineBeforePrintlnRule(ruleData.maxInt)
                else -> throw IllegalArgumentException("Unknown rule type: ${ruleData.type}")
            }
        }
        return DisabledRule()
    }
}
