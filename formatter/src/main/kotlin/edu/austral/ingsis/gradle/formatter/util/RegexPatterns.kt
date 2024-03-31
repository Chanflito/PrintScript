package edu.austral.ingsis.gradle.formatter.util

interface RuleType {
    val regex: Regex
}

object SpaceBeforeColon : RuleType {
    override val regex: Regex = Regex("(\\w+)(\\s*):")
}

object SpaceAfterColon : RuleType {
    override val regex: Regex = Regex(":(\\s*)(\\w+)")
}

object SpaceAroundEqual : RuleType {
    override val regex: Regex = Regex("(\\w+)(\\s*)(=)(\\s*)(\\w+)")
}

object NewlineBeforePrintln : RuleType {
    override val regex: Regex = Regex("(?<=\\W)println")
}
