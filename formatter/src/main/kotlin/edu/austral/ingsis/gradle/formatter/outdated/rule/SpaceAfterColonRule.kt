package edu.austral.ingsis.gradle.formatter.outdated.rule

import edu.austral.ingsis.gradle.formatter.util.RegexPatterns

class SpaceAfterColonRule : Rule {
    override fun applyRule(code: String): String {
        val regex = RegexPatterns.spaceAfterColon
        val replaced = code.replace(regex, ": $2")
        return replaced
    }
}
