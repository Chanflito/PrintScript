package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.contentAfterColonRegex

class SpaceAfterColonRule : Rule {
    override fun applyRule(code: String): String {
        val replaced = code.replace(contentAfterColonRegex, ": $2")
        return replaced
    }
}
