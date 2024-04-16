package edu.austral.ingsis.gradle.formatter.rule

class DisabledRule : Rule {
    override fun applyRule(code: String): String {
        return code
    }
}
