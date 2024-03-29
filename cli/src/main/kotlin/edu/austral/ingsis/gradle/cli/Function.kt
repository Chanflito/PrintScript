package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.interpreter.Interpreter
import edu.austral.ingsis.gradle.lexer.util.createComposeLexer
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.adapter.FileToJsonAdapter
import java.io.File

interface Function<in T, out O> {
    fun evaluate(input: T): O
}

class ExecuteFunction : Function<String, List<Any>> {
    override fun evaluate(input: String): List<Any> {
        val lexer = createComposeLexer()
        val tokenList = lexer.splitIntoTokens(input)
        val parser = createComposeParser()
        val astNode = parser.parse(InputContext(tokenList, 0))
        return Interpreter().interpret(astNode.first)
    }
}

class AnalyzeFunction : Function<Pair<String, File>, ReportResult> {
    override fun evaluate(input: Pair<String, File>): ReportResult {
        val lexer = createComposeLexer()
        val tokenList = lexer.splitIntoTokens(input.first)
        val parser = createComposeParser()
        val astNode = parser.parse(InputContext(tokenList, 0))
        val sca = FileToJsonAdapter().adapt(input.second)
        return sca.verify(astNode.first)
    }
}

// TODO: implement format function when formatter is done.
