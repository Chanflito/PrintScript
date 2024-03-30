package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.cli.adapter.FileAdapter
import edu.austral.ingsis.gradle.cli.utils.delay
import java.io.File

class Cli {
    init {
        startCli()
    }

    private fun startCli() {
        welcome()
        do {
            showOptions()
            val option = readln()
            try {
                when (option) {
                    "1" -> executeCase()
                    "2" -> formatCase()
                    "3" -> analyzeCase()
                    "4" -> break
                    else -> throw IllegalArgumentException("Invalid option")
                }
            } catch (e: Exception) {
                println(e.message)
            }
            delay(2000)
        } while (true)
    }

    private fun showOptions() {
        println("You have the following options to start")
        println("1.Execute")
        println("2.Formatting")
        println("3.Analyze")
        println("4.Exit")
    }

    private fun executeCase() {
        println("Indicate source file")
        val source = readln()
        val file = validateFile(source)
        val content = FileAdapter().adapt(file)
        ExecuteFunction().evaluate(content)
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
    }

    private fun formatCase() {
        throw NotImplementedError("Formatting is not implemented yet")
    }

    private fun validateFile(fileName: String): File {
        val file = File(fileName)
        if (!file.exists()) {
            throw IllegalArgumentException("File $fileName does not exist")
        }
        return file
    }

    private fun welcome() {
        println(
            "██████╗ ██████╗ ██╗███╗   ██╗████████╗███████╗ ██████╗██████╗ ██╗██████╗ ████████╗\n" +
                "██╔══██╗██╔══██╗██║████╗  ██║╚══██╔══╝██╔════╝██╔════╝██╔══██╗██║██╔══██╗╚══██╔══╝\n" +
                "██████╔╝██████╔╝██║██╔██╗ ██║   ██║   ███████╗██║     ██████╔╝██║██████╔╝   ██║   \n" +
                "██╔═══╝ ██╔══██╗██║██║╚██╗██║   ██║   ╚════██║██║     ██╔══██╗██║██╔═══╝    ██║   \n" +
                "██║     ██║  ██║██║██║ ╚████║   ██║   ███████║╚██████╗██║  ██║██║██║        ██║   \n" +
                "╚═╝     ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝╚═╝        ╚═╝   \n",
        )

        println(" Welcome to PrintScript 1.0! ")

        delay(2000)
    }
}
