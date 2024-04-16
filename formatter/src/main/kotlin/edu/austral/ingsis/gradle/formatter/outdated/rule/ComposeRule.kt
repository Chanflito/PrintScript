package edu.austral.ingsis.gradle.formatter.outdated.rule

class ComposeRule(private val rules: List<Rule>) : Rule {
    override fun applyRule(code: String): String {
        var result = code
        rules.forEach { rule ->
            result = rule.applyRule(result)
        }
        return result
    }
}
