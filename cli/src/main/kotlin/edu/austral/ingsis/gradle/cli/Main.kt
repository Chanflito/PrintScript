package edu.austral.ingsis.gradle.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument

class Hello : CliktCommand(name = "printscript", help = "Printscript") {
    private val sourceFile by argument(help = "The source file to run")
    private val version by argument(help = "PrintScript version to run")

    override fun run() {
        println("Current version: $version")
    }
}

fun main(args: Array<String>) = Hello().main(args)
