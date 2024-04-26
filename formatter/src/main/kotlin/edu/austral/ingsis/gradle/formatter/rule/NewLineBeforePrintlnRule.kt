package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.lineBeforePrintlnRegex

class NewLineBeforePrintlnRule(private val maxInt: Int?) : Rule {
    override fun applyRule(code: String): String {
        val spaces = maxInt?.let { "\n".repeat(it) } ?: ""
        val replaced = code.replace(lineBeforePrintlnRegex, "$spaces$0")
        return replaced
    }
}
