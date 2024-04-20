package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.formatter.createDefaultFormatter
import edu.austral.ingsis.gradle.formatter.createDefaultRules
import edu.austral.ingsis.gradle.formatter.createIfBlockRules
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.Rules
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.createInterpreterManager
import edu.austral.ingsis.gradle.iterator.LexerIterator
import edu.austral.ingsis.gradle.iterator.ParserIterator
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.impl.ProgramNodeParser
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.adapter.FileToJsonAdapter
import java.io.File

interface Function<in T, out O> {
    fun evaluate(
        input: T,
        version: String,
    ): O
}

class ExecuteFunction : Function<String, List<Any>> {
    override fun evaluate(
        input: String,
        version: String,
    ): List<Any> {
        val versionAdapted = version.take(3)
        val lexer = LexerDirector().createComposeLexer(versionAdapted)
        val lexerIterator = LexerIterator(lexer, input.byteInputStream())
        val composeParser = createComposeParser()
        val parserIterator = ParserIterator(lexerIterator, composeParser)
        var context = Context()
        while (parserIterator.hasNext()) {
            val interpreterManager = createInterpreterManager()
            val ast: AST? = parserIterator.next()
            val homeDir = System.getProperty("user.home")
            val filePath = "$homeDir/Desktop/tokencomparison.txt"
            File(filePath).writeText(ast.toString())
            val interpreter = interpreterManager.getInterpreter(ast!!)
            val interpreterResult = interpreter.interpret(ast, context, interpreterManager)
            when (interpreterResult) {
                is InterpretResult.ContextResult -> context = context.update(interpreterResult.context)
                else -> throw RuntimeException("Interpreter result not supported")
            }
        }
        return context.getPrintValues()
    }
}

class AnalyzeFunction : Function<Pair<String, File>, ReportResult> {
    override fun evaluate(
        input: Pair<String, File>,
        version: String,
    ): ReportResult {
        val ast = createAstNode(input, version)
        val sca = FileToJsonAdapter().adapt(input.second)
        println("Analyzing...\n")
        return sca.verify(ast.first)
    }
}

class FormatFunction : Function<Pair<String, File>, String> {
    override fun evaluate(
        input: Pair<String, File>,
        version: String,
    ): String {
        val ast = createAstNode(input, version)
        val formatter = createDefaultFormatter()
        val defaultRules = ComposeRule(createDefaultRules(input.second.absolutePath))
        val ifBlockRules = ComposeRule(createIfBlockRules(input.second.absolutePath))
        val rules = Rules(defaultRules, ifBlockRules)
        println("Formatting...\n")
        return formatter.format(ast.first, rules)
    }
}

private fun createAstNode(
    input: Pair<String, File>,
    version: String,
): Pair<AST, Int> {
    val adaptedVersion = version.take(3)
    val lexer = LexerDirector().createComposeLexer(adaptedVersion)
    println("Lexing...")
    val tokenList = lexer.splitIntoTokens(input.first)
    val parser = ProgramNodeParser()
    println("Parsing...")
    return parser.parse(InputContext(tokenList))
}
