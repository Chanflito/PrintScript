package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.spaceAroundEqualRegex

class SpaceAroundEqualRule : Rule {
    override fun applyRule(code: String): String {
        val replaced = code.replace(spaceAroundEqualRegex, "$1 = $5")
        return replaced
    }
}
