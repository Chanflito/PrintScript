package edu.austral.ingsis.gradle.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.file
import edu.austral.ingsis.gradle.cli.adapter.FileAdapter
import java.io.File

class Cli : CliktCommand(help = "Run a Printscript file") {
    companion object {
        // TODO get version and release date from gradle
        const val VERSION = "1.0.1-SNAPSHOT"
        const val RELEASE_DATE = "Apr 4, 2024, 11:16 AM GMT-3"
        val USER_OS = System.getProperty("os.name").toString()

        const val FORMAT_RULES_FILE_PATH = "/format_rules.json"
        const val ANALYZE_RULES_FILE_PATH = "/sca_rules.json"
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
            "format" -> formatCase()
            "analyze" -> analyzeCase()
            "execute" -> executeCase()
        }
    }

    private fun executeCase() {
        println("Executing...\n")
        val stringFile = FileAdapter().adapt(source)
        ExecuteFunction().evaluate(stringFile)
    }

    private fun analyzeCase() {
        val rulesFile = File(javaClass.getResource(ANALYZE_RULES_FILE_PATH).file)
        val stringFile = FileAdapter().adapt(source)
        val reportResult = AnalyzeFunction().evaluate(Pair(stringFile, rulesFile))
        println(reportResult.toString())
    }

    private fun formatCase() {
        val rulesFile = File(javaClass.getResource(FORMAT_RULES_FILE_PATH).file)
        val stringFile = FileAdapter().adapt(source)
        val formattedContent = FormatFunction().evaluate(Pair(stringFile, rulesFile))
        source?.writeText(formattedContent)
    }
}

fun main(args: Array<String>) {
    Cli().main(args)
}
