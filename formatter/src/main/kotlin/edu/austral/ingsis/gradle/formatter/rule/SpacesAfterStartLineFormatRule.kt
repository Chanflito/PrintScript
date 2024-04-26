package edu.austral.ingsis.gradle.formatter.rule

class SpacesAfterStartLineFormatRule(private val maxInt: Int?) : Rule {
    override fun applyRule(code: String): String {
        val spaces = maxInt?.let { " ".repeat(it) } ?: ""
        val replaced = "$spaces$code"
        return replaced
    }
}
