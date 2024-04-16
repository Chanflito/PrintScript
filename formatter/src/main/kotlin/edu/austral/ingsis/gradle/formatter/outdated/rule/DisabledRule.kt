package edu.austral.ingsis.gradle.formatter.outdated.rule

class DisabledRule : Rule {
    override fun applyRule(code: String): String {
        return code
    }
}
