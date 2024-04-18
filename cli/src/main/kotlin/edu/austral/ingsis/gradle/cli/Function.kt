package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.formatter.createDefaultFormatter
import edu.austral.ingsis.gradle.formatter.createDefaultRules
import edu.austral.ingsis.gradle.formatter.createIfBlockRules
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.adapter.FileToJsonAdapter
import java.io.File

interface Function<in T, out O> {
    fun evaluate(
        input: T,
        version: String,
    ): O
}

// TODO wait interpreter to be refactored
class ExecuteFunction : Function<String, List<Any>> {
    override fun evaluate(
        input: String,
        version: String,
    ): List<Any> {
        return emptyList()
        /*val lexer = LexerDirector().createComposeLexer("1.1")
        val tokenList = lexer.splitIntoTokens(input)
        val parser = createComposeParser()
        val astNode = parser.parse(InputContext(tokenList, 0))
        return Interpreter().interpret(astNode.first)
    }*/
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
        val rules = ComposeRule(createDefaultRules(input.second.absolutePath))
        val ifBlockRules = ComposeRule(createIfBlockRules(input.second.absolutePath))
        println("Formatting...\n")
        return formatter.format(ast.first, rules, ifBlockRules)
    }
}

// TODO wait parser to be refactored
private fun createAstNode(
    input: Pair<String, File>,
    version: String,
): Pair<AST, Int> {
    TODO()
    /*
    val lexer = LexerDirector().createComposeLexer("1.1")
    println("Lexing...")
    val tokenList = lexer.splitIntoTokens(input.first)

    val parser = createComposeParser()
    println("Parsing...")
    return parser.parse(InputContext(tokenList, 0))
     */
}
