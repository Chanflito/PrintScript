package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.contentAroundEqualRegex

class SpaceAroundEqualRule : Rule {
    override fun applyRule(code: String): String {
        val replaced = code.replace(contentAroundEqualRegex, "$1 = $5")
        return replaced
    }
}
