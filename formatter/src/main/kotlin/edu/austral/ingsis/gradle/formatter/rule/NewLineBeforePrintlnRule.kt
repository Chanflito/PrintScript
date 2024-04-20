package edu.austral.ingsis.gradle.formatter.rule

import edu.austral.ingsis.gradle.formatter.util.newlineBeforePrintlnRegex

class NewLineBeforePrintlnRule(private val maxInt: Int?) : Rule {
    override fun applyRule(code: String): String {
        val spaces =
            if (maxInt != null) {
                "\n".repeat(maxInt)
            } else {
                ""
            }
        val replaced = code.replace(newlineBeforePrintlnRegex, "$spaces$0")
        return replaced
    }
}
