package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.spaceAfterColonRegex

class SpaceAfterColonRule : Rule {
    override fun applyRule(code: String): String {
        val replaced = code.replace(spaceAfterColonRegex, ": $2")
        return replaced
    }
}
