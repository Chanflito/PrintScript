package edu.austral.ingsis.gradle.formatter.util

class RuleApplier {
    fun applyRules(
        formattedString: String,
        rules: List<Rule>,
    ): String {
        var result = formattedString

        rules.forEach { rule ->
            when (rule.type) {
                "spaceBeforeColon" -> {
                    val regex = Regex("(\\w+)(\\s*):")
                    result =
                        if (rule.allowed) {
                            result.replace(regex, "$1 :")
                        } else {
                            result.replace(regex, "$1:")
                        }
                }
                "spaceAfterColon" -> {
                    val regex = Regex(":(\\s*)(\\w+)")
                    result =
                        if (rule.allowed) {
                            result.replace(regex, ": $2")
                        } else {
                            result.replace(regex, ":$2")
                        }
                }
                "spaceAroundEqual" -> {
                    val regex = Regex("(\\w+)(\\s*)(=)(\\s*)(\\w+)")
                    result =
                        if (rule.allowed) {
                            result.replace(regex, "$1 = $5")
                        } else {
                            result.replace(regex, "$1=$5")
                        }
                }
                "newlineBeforePrintln" -> {
                    val regex = Regex("(?<=\\W)println")
                    val spaces =
                        if (rule.maxInt != null) {
                            "\n".repeat(rule.maxInt)
                        } else {
                            ""
                        }
                    result =
                        if (rule.allowed) {
                            result.replace(regex, "$spaces$0")
                        } else {
                            result.replace(regex, "println")
                        }
                }
                else -> {
                    // No se hace nada si no se encuentra la regla
                }
            }
        }

        return result
    }
}
