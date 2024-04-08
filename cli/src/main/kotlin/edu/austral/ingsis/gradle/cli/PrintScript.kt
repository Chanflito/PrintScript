package edu.austral.ingsis.gradle.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.file
import edu.austral.ingsis.gradle.cli.adapter.FileAdapter

class PrintScript : CliktCommand(help = "Run a Printscript file") {
    companion object {
        const val VERSION = "1.0.0"
        const val RELEASE_DATE = "Apr 4, 2024, 11:16 AM GMT-3"
        const val USER_OS = "Linux"

        // TODO found the way to handle with path in different modules
        const val FORMAT_RULES_FILE_PATH = "formatter/src/main/resources/format_rules.json"
    }

    private val argument by argument(help = "Printscript arguments available")
        .choice("execute", "format", "analyze").optional()

    private val source by option(help = "The source file to run")
        .file(mustExist = true)

    // TODO add only numeric arguments with x.x.x format
    private val version by option(help = "Printscript version to run")
        .default(VERSION)

    override fun run() {
        println("\nPrintScript: $version ($RELEASE_DATE) on $USER_OS\n")

        when (argument) {
            "format" -> println("Formatting")
            "analyze" -> println("Analyzing")
            "execute" -> executeCase()
        }
    }

    private fun executeCase() {
        println("Executing...")
        val stringFile = FileAdapter().adapt(source)
        ExecuteFunction().evaluate(stringFile)
    }

    /*    private fun formatCase() {
            println("Formatting...")
            val rulesFile = File(FORMAT_RULES_FILE_PATH)
            val stringFile = FileAdapter().adapt(source)
            val formattedContent = FormatFunction().evaluate(Pair(stringFile, rulesFile))
            source?.writeText(formattedContent)
        }

        private fun analyzeCase() {
            println("Indicate source file")
            val source = readln()
            println("Indicate the config file")
            val config = readln()
            val sourceFile = validateFile(source)
            val configFile = validateFile(config)
            val content = FileAdapter().adapt(sourceFile)
            val reportResult = AnalyzeFunction().evaluate(Pair(content, configFile))
            println(reportResult.toString())
        }*/
}

fun main(args: Array<String>) = PrintScript().main(args)
