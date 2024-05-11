package edu.austral.ingsis.gradle.sca.adapter.json

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.adapter.ScaAdapter
import edu.austral.ingsis.gradle.sca.rule.DisabledRule
import edu.austral.ingsis.gradle.sca.rule.PrintlnRule
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

class JsonReadInputAdapter(private val expectedInput: String = "enforce_literal_or_identifier_in_read_input_rule") :
    ScaAdapter<JsonObject> {
    override fun adapt(input: JsonObject): Rule<AST> {
        if (input.containsKey(expectedInput)) {
            val isEnable = input.getValue(expectedInput).jsonPrimitive.boolean // If it's not boolean fails.
            if (isEnable) {
                return PrintlnRule()
            }
        }
        return DisabledRule()
    }
}
