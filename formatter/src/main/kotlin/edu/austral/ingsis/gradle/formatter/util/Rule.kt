package edu.austral.ingsis.gradle.formatter.util

import kotlinx.serialization.Serializable

@Serializable
data class Rule(
    val type: String,
    val allowed: Boolean,
    val maxInt: Int? = null,
)

@Serializable
data class RuleJson(val type: String, val allowed: Boolean, val maxInt: Int? = null)

@Serializable
data class RulesJson(val rules: List<RuleJson>)
