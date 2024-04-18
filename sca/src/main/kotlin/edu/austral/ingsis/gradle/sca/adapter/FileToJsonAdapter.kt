package edu.austral.ingsis.gradle.sca.adapter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.adapter.json.JsonComposeAdapter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import java.io.File

class FileToJsonAdapter : ScaAdapter<File> {
    override fun adapt(input: File): Rule<AST> {
        if (!input.exists()) throw Exception("File does not exist")
        val fileContent = input.readText()
        val jsonObject: JsonObject = Json.parseToJsonElement(fileContent).jsonObject
        return JsonComposeAdapter().adapt(jsonObject)
    }
}
