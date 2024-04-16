package edu.austral.ingsis.gradle.formatter.rule

interface Rule {
    fun applyRule(code: String): String
}
