package edu.austral.ingsis.gradle.iterator

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.interpreter.ComposeInterpreter
import edu.austral.ingsis.gradle.interpreter.util.KotlinEnvReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinInputReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinPrinter
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import edu.austral.ingsis.gradle.parser.factory.createComposeParser
import java.io.InputStream

fun execute(input: InputStream) { // TODO: Refactor this and interpreter.
    val fileBuffer = FileBuffer(input)
    val lexer = LexerDirector().createComposeLexer("1.1")
    val lexerIterator = LexerIterator(lexer, fileBuffer.getFileBuffered())
    val parserIterator = ParserIterator(lexerIterator, createComposeParser())
    var interpreter =
        ComposeInterpreter(
            emitter = KotlinPrinter(),
            envReader = KotlinEnvReader(),
            inputReader = KotlinInputReader(),
        )
    while (parserIterator.hasNext()) {
        val ast = parserIterator.next()
        if (ast != null) {
            interpreter = interpreter.interpretAndUpdateContext(ast)
        }
    }
}

fun compareTokenListWithIterator(
    tokens: List<Token>,
    tokensUntilEndOfStatement: List<String>,
): Boolean {
    val tokensConvertedToList = tokens.map { "${it.value},${it.tokenType::class.simpleName}" }
    if (tokensConvertedToList.size != tokensUntilEndOfStatement.size) {
        return false
    }

    for ((index, tokenString) in tokensUntilEndOfStatement.withIndex()) {
        val expectedTokenString = tokensConvertedToList[index]

        val tokenElements = tokenString.split(",").take(2)
        val expectedTokenElements = expectedTokenString.split(",").take(2)

        if (tokenElements != expectedTokenElements) {
            println("First two elements of line ${index + 1} do not match:")
            println("Output: ${tokenElements.joinToString(",")}")
            println("Expected: ${expectedTokenElements.joinToString(",")}")
            return false
        }
    }
    return true
}
