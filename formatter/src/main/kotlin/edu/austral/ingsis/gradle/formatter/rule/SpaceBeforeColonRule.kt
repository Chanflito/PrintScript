package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.contentBeforeColonRegex

class SpaceBeforeColonRule : Rule {
    override fun applyRule(code: String): String {
        val replaced = code.replace(contentBeforeColonRegex, "$1 :")
        return replaced
    }
}
