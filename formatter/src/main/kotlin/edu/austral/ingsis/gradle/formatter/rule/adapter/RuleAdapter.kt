package edu.austral.ingsis.gradle.formatter.rule.adapter

import edu.austral.ingsis.gradle.formatter.rule.DisabledRule
import edu.austral.ingsis.gradle.formatter.rule.NewLineBeforePrintlnRule
import edu.austral.ingsis.gradle.formatter.rule.Rule
import edu.austral.ingsis.gradle.formatter.rule.SpaceAfterColonRule
import edu.austral.ingsis.gradle.formatter.rule.SpaceAroundEqualRule
import edu.austral.ingsis.gradle.formatter.rule.SpaceBeforeColonRule
import edu.austral.ingsis.gradle.formatter.rule.SpacesAfterStartLineFormatRule

class RuleAdapter {
    fun adapt(ruleJson: RuleJson): Rule {
        if (ruleJson.allowed) {
            return when (ruleJson.type) {
                "spaceBeforeColon" -> SpaceBeforeColonRule()
                "spaceAfterColon" -> SpaceAfterColonRule()
                "spaceAroundEqual" -> SpaceAroundEqualRule()
                "newlineBeforePrintln" -> NewLineBeforePrintlnRule(ruleJson.maxInt)
                "spacesAfterStartLine" -> SpacesAfterStartLineFormatRule(ruleJson.maxInt)
                else -> throw IllegalArgumentException("Unknown rule type: ${ruleJson.type}")
            }
        }
        return DisabledRule()
    }
}
