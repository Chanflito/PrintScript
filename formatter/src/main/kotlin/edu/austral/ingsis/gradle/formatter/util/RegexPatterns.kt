package edu.austral.ingsis.gradle.formatter.util

val contentBeforeColonRegex = Regex("(\\w+)(\\s*):")
val contentAfterColonRegex = Regex(":(\\s*)(\\w+)")
val contentAroundEqualRegex = Regex("(\\w+)(\\s*)(=)(\\s*)(\"?\\w+\"?)")
val lineBeforePrintlnRegex = Regex("(?<=\\W)println")
