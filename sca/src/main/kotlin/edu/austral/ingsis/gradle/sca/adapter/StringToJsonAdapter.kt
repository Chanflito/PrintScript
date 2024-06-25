package edu.austral.ingsis.gradle.sca.adapter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.adapter.json.JsonComposeAdapter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class StringToJsonAdapter : ScaAdapter<String> {
    override fun adapt(input: String): Rule<AST> {
        return JsonComposeAdapter().adapt(Json.parseToJsonElement(input).jsonObject)
    }
}
