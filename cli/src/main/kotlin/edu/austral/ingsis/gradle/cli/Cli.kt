package edu.austral.ingsis.gradle.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.file
import edu.austral.ingsis.gradle.cli.adapter.FileAdapter

class Cli : CliktCommand(help = "Run a PrintScript file") {
    companion object {
        val RELEASE_VERSION = System.getenv("RELEASE_VERSION").toString()
        val RELEASE_VERSIONS: List<String> =
            System.getenv("RELEASE_VERSIONS").toString().split(",")

        val RELEASE_DATE = System.getenv("RELEASE_DATE").toString()
        val USER_OS = System.getProperty("os.name").toString()
    }

    private val argument by argument(help = "PrintScript arguments available")
        .choice("execute", "format", "analyze").optional()

    private val rulesFile by option("-r", "--rules", help = "The rules file to use")
        .file(mustExist = true)

    private val source by option("-s", "--source", help = "The source file to run")
        .file(mustExist = true)

    private val version by option(help = "PrintScript version to run")
        .default(RELEASE_VERSION)
        .validate {
            require(it in RELEASE_VERSIONS) { "Available versions: ${RELEASE_VERSIONS.joinToString(", ")}" }
        }

    override fun run() {
        println("\nPrintScript: $version ($RELEASE_DATE) on $USER_OS\n")

        when (argument) {
            "execute" -> executeCase()
            "analyze" -> analyzeCase()
            "format" -> formatCase()
        }
    }

    private fun executeCase() {
        println("Executing...\n")
        val stringFile = FileAdapter().adapt(source)
        ExecuteFunction().evaluate(stringFile, version)
    }

    private fun analyzeCase() {
        val stringCode = FileAdapter().adapt(source)
        val reportResult = AnalyzeFunction().evaluate(Pair(stringCode, rulesFile!!), version)
        println(reportResult.toString())
    }

    private fun formatCase() {
        val stringCode = FileAdapter().adapt(source)
        val formattedContent = FormatFunction().evaluate(Pair(stringCode, rulesFile!!), version)
        source?.writeText(formattedContent)
        println("\nFormatted file: $source")
    }
}

fun main(args: Array<String>) {
    Cli().main(args)
}
