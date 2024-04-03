package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.RegexPatterns

class SpaceBeforeColonRule : Rule {
    override fun applyRule(code: String): String {
        val regex = RegexPatterns.spaceBeforeColon
        val replaced = code.replace(regex, "$1 :")
        return replaced
    }
}
