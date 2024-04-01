package edu.austral.ingsis.gradle.formatter.rule.adapter

import kotlinx.serialization.json.Json
import java.io.File

class RuleParser {
    fun parseRulesFromFile(filePath: String): List<RuleData> {
        val json = File(filePath).readText()
        return parseRules(json)
    }

    private fun parseRules(json: String): List<RuleData> {
        val ruleJsonList = Json.decodeFromString<List<RuleJson>>(json)
        return ruleJsonList.map { ruleJson ->
            RuleData(
                type = ruleJson.type,
                allowed = ruleJson.allowed,
                maxInt = ruleJson.maxInt,
            )
        }
    }
}
