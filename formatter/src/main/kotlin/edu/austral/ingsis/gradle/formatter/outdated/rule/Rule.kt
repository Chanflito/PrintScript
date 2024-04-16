package edu.austral.ingsis.gradle.formatter.outdated.rule

interface Rule {
    fun applyRule(code: String): String
}
