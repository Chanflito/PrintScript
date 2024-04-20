package edu.austral.ingsis.gradle.formatter.util

val spaceBeforeColonRegex = Regex("(\\w+)(\\s*):")
val spaceAfterColonRegex = Regex(":(\\s*)(\\w+)")
val spaceAroundEqualRegex = Regex("(\\w+)(\\s*)(=)(\\s*)(\"[^\"]*\"|\\w+)")
val newlineBeforePrintlnRegex = Regex("(?<=\\W)println")
