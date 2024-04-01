package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.RegexPatterns

class NewLineBeforePrintlnRule(private val maxInt: Int?) : Rule {
    override fun applyRule(code: String): String {
        val regex = RegexPatterns.newlineBeforePrintln

        val spaces =
            if (maxInt != null) {
                "\n".repeat(maxInt)
            } else {
                ""
            }
        val replaced = code.replace(regex, "$spaces$0")
        return replaced
    }
}
