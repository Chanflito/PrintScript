package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.formatter.outdated.Formatter
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleParser
import edu.austral.ingsis.gradle.interpreter.Interpreter
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
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
        val lexer = LexerDirector().createComposeLexer("1.1")
        val tokenList = lexer.splitIntoTokens(input)
        val parser = createComposeParser()
        val astNode = parser.parse(InputContext(tokenList, 0))
        return Interpreter().interpret(astNode.first)
    }
}

class AnalyzeFunction : Function<Pair<String, File>, ReportResult> {
    override fun evaluate(input: Pair<String, File>): ReportResult {
        val astNode = createAstNode(input)
        val sca = FileToJsonAdapter().adapt(input.second)
        println("Analyzing...\n")
        return TODO()
        // return sca.verify(astNode.first)
    }
}

class FormatFunction : Function<Pair<String, File>, String> {
    override fun evaluate(input: Pair<String, File>): String {
        val astNode = createAstNode(input)
        val formatter = Formatter()
        val rules = RuleParser().parseRulesFromFile(input.second.path)
        return formatter.format(astNode.first, rules).replace("\"\"", "\"")
    }
}

fun createAstNode(input: Pair<String, File>): Pair<ASTNode, Int> {
    val lexer = LexerDirector().createComposeLexer("1.1")
    println("Lexing...")
    val tokenList = lexer.splitIntoTokens(input.first)
    val parser = createComposeParser()
    println("Parsing...")
    return parser.parse(InputContext(tokenList, 0))
}
