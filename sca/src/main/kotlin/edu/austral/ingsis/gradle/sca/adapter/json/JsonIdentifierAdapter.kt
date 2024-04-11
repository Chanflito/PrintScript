package edu.austral.ingsis.gradle.sca.adapter.json

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.sca.IdentifierRule
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.adapter.ScaAdapter
import edu.austral.ingsis.gradle.sca.util.CamelCaseRule
import edu.austral.ingsis.gradle.sca.util.identifierRuleWithCustomErrorMap
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class JsonIdentifierAdapter(private val expectedInput: String = "identifierRule") : ScaAdapter<JsonObject> {
    // This one should be always implemented, so if is not defined throws exception
    // camelCase and snakeCase
    override fun adapt(input: JsonObject): Rule<AST> {
        if (input.containsKey(expectedInput)) {
            val case = input.getValue(expectedInput).jsonPrimitive.content
            return getIdentifierRule(case)
        }
        throw Exception("Identifier rule is not defined in json file")
    }

    private fun getIdentifierRule(case: String): Rule<AST> {
        // If is not defined in the map, then is camelCase as default convention of the project
        val identifierRuleType = IDENTIFIER_MAP.getOrDefault(case, CamelCaseRule)
        return IdentifierRule(identifierRuleType, identifierRuleWithCustomErrorMap)
    }
}
