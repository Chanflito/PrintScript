package edu.austral.ingsis.gradle.sca.util

class RegexPatterns {
    companion object {
        val CAMEL_CASE = "([a-z]+[A-Z]+\\w+)+".toRegex()
        val SNAKE_CASE = "([a-z]+_\\w+)+".toRegex()
    }
}
