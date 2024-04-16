package edu.austral.ingsis.gradle.formatter.outdated.rule

import edu.austral.ingsis.gradle.formatter.util.RegexPatterns

class SpaceAroundEqualRule : Rule {
    override fun applyRule(code: String): String {
        val regex = RegexPatterns.spaceAroundEqual
        val replaced = code.replace(regex, "$1 = $5")
        return replaced
    }
}
