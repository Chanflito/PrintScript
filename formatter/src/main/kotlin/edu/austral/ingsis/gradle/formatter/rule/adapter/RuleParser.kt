package edu.austral.ingsis.gradle.formatter.rule.adapter

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

class RuleParser {
    fun parseRulesFromFile(
        filePath: String,
        ruleContext: String,
    ): List<RuleJson> {
        val json = File(filePath).readText()
        return parseRules(json, ruleContext)
    }

    private fun parseRules(
        json: String,
        ruleContext: String,
    ): List<RuleJson> {
        val jsonObject = Json.parseToJsonElement(json).jsonObject
        val contextRulesJsonArray = jsonObject[ruleContext]!!.jsonArray

        val contextRules = parseRuleDataArray(contextRulesJsonArray)

        return contextRules
    }

    private fun parseRuleDataArray(jsonArray: JsonArray): List<RuleJson> {
        return jsonArray.flatMap { nestedArray ->
            nestedArray.jsonArray.map { element ->
                val ruleJson = element.jsonObject
                RuleJson(
                    type = ruleJson["type"]!!.jsonPrimitive.content,
                    allowed = ruleJson["allowed"]!!.jsonPrimitive.boolean,
                    maxInt = ruleJson["maxInt"]?.jsonPrimitive?.int,
                )
            }
        }
    }
}
