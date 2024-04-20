package edu.austral.ingsis.gradle.lexer.util

import edu.austral.ingsis.gradle.common.token.Token
import java.io.File

fun writeTokensToFile(
    tokens: List<Token>,
    filePath: String,
): File {
    val outputFile = File(filePath)

    outputFile.createNewFile()

    outputFile.printWriter().use { out ->
        tokens.forEach { token ->
            out.println(
                "${token.value},${token.tokenType::class.simpleName}," +
                    "(${token.tokenPosition.startPosition.row},${token.tokenPosition.startPosition.column})," +
                    "(${token.tokenPosition.endPosition.row},${token.tokenPosition.endPosition.column})",
            )
        }
    }
    return outputFile
}

fun convertFileToString(filePath: String): String {
    return File(filePath).readText()
}

fun compareFiles(
    outputFile: File,
    expectedFilePath: String,
): Boolean {
    val outputFileLines = outputFile.readLines()
    val expectedFileLines = File(expectedFilePath).readLines()

    if (outputFileLines.size != expectedFileLines.size) {
        println("The files have different numbers of lines.")
        return false
    }

    for ((index, outputLine) in outputFileLines.withIndex()) {
        val expectedLine = expectedFileLines[index]

        val outputElements = outputLine.split(",").take(2)
        val expectedElements = expectedLine.split(",").take(2)

        if (outputElements != expectedElements) {
            println("First two elements of line ${index + 1} do not match:")
            println("Output: ${outputElements.joinToString(",")}")
            println("Expected: ${expectedElements.joinToString(",")}")
            return false
        }
    }

    return true
}
