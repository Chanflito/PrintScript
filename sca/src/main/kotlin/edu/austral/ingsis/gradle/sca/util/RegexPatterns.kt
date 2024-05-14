package edu.austral.ingsis.gradle.sca.util

interface IdentifierRuleType {
    val regex: Regex
}

object CamelCaseRule : IdentifierRuleType {
    // Add and or for cases where is not camelCase such as "number" or "NUMBER"
    // ^([a-z]+|[A-Z]+)$
    override val regex = "^[a-zA-Z]+([A-Z][a-z]+)+\$|^([a-z]+|[A-Z]+)\$".toRegex()
}

// Add and or for cases where is not snake_case such as "number" or "NUMBER"
object SnakeCaseRule : IdentifierRuleType {
    override val regex = "([a-z]+_\\w+)+|^([a-z]+|[A-Z]+)\$".toRegex()
}
