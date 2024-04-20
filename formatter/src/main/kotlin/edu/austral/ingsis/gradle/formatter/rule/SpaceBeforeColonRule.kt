package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.spaceBeforeColonRegex

class SpaceBeforeColonRule : Rule {
    override fun applyRule(code: String): String {
        val replaced = code.replace(spaceBeforeColonRegex, "$1 :")
        return replaced
    }
}
