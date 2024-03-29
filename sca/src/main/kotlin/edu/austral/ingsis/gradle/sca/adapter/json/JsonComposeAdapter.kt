package edu.austral.ingsis.gradle.sca.adapter.json

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.sca.ComposeRule
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.adapter.ScaAdapter
import kotlinx.serialization.json.JsonObject

class JsonComposeAdapter(private val adapters: List<ScaAdapter<JsonObject>> = listOf(JsonIdentifierAdapter(), JsonPrintlnAdapter())) :
    ScaAdapter<JsonObject> {
    override fun adapt(input: JsonObject): Rule<ASTNode> {
        return ComposeRule(adapters.map { it.adapt(input) })
    }
}
