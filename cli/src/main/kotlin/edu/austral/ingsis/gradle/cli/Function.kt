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
        println("Execute function selected")
        val lexer = createComposeLexer()
        println("Lexing...")
        val tokenList = lexer.splitIntoTokens(input)
        val parser = createComposeParser()
        println("Parsing...")
        val astNode = parser.parse(InputContext(tokenList, 0))
        println("Interpreting...")
        return Interpreter().interpret(astNode.first)
    }
}

class AnalyzeFunction : Function<Pair<String, File>, ReportResult> {
    override fun evaluate(input: Pair<String, File>): ReportResult {
        println("Analyze function selected")
        val lexer = createComposeLexer()
        println("Lexing...")
        val tokenList = lexer.splitIntoTokens(input.first)
        val parser = createComposeParser()
        println("Parsing...")
        val astNode = parser.parse(InputContext(tokenList, 0))
        val sca = FileToJsonAdapter().adapt(input.second)
        println("Analyzing...")
        return sca.verify(astNode.first)
    }
}
