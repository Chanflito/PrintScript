package edu.austral.ingsis.gradle.formatter.util

import kotlinx.serialization.json.Json
import java.io.File

class RuleParser {
    fun parseRulesFromFile(filePath: String): List<Rule> {
        val json = File(filePath).readText()
        return parseRules(json)
    }

    private fun parseRules(json: String): List<Rule> {
        val rulesJson = Json.decodeFromString<RulesJson>(json)
        return rulesJson.rules.map { ruleJson ->
            Rule(
                type = ruleJson.type,
                allowed = ruleJson.allowed,
                maxInt = ruleJson.maxInt,
            )
        }
    }
}
