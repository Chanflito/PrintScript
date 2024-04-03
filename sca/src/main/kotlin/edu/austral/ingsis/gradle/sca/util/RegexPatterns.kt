package edu.austral.ingsis.gradle.sca.util

interface IdentifierRuleType {
    val regex: Regex
}

object CamelCaseRule : IdentifierRuleType {
    override val regex = "([a-z]+[A-Z]+\\w+)+".toRegex()
}

object SnakeCaseRule : IdentifierRuleType {
    override val regex = "([a-z]+_\\w+)+".toRegex()
}
