package edu.austral.ingsis.gradle.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional

class PrintScript : CliktCommand(name = "printscript", help = "Printscript") {
    override fun run() = Unit
}
class Run : CliktCommand(help = "Run a Printscript file") {

    private val sourceFile by argument(help = "The source file to run")
    private val version by argument(help = "Printscript version to run (optional)").optional()
    override fun run() {
        // CLIUtils.runAppWithFunction(File(sourceFile), ExecuteFunction())
        println("Current version: $version")
//        StreamedExecution(FileInputStream(File(sourceFile)), version.toString(), ConsoleInputter(), ConsolePrintOutputter()).execute()
    }
}

fun main(args: Array<String>) = PrintScript().subcommands(Run()).main(args)