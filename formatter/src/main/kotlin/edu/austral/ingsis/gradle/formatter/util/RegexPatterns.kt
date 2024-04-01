package edu.austral.ingsis.gradle.formatter.util

class RegexPatterns {
    companion object {
        val spaceBeforeColon = Regex("(\\w+)(\\s*):")
        val spaceAfterColon = Regex(":(\\s*)(\\w+)")
        val spaceAroundEqual = Regex("(\\w+)(\\s*)(=)(\\s*)(\"[^\"]*\"|\\w+)") // Added with quotes
        val newlineBeforePrintln = Regex("(?<=\\W)println")
    }
}
