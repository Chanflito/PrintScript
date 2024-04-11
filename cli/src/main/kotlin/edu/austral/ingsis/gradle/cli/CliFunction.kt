package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.formatter.Formatter
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleParser
import edu.austral.ingsis.gradle.interpreter.Interpreter
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.adapter.FileToJsonAdapter
import java.io.File

interface CliFunction<in T, out O> {
    fun evaluate(input: T): O
}

class ExecuteCliFunction : CliFunction<String, List<Any>> {
    override fun evaluate(input: String): List<Any> {
        println("Execute function selected")
        val lexer = LexerDirector().createComposeLexer("1.0") // Change this to select version of printscript to use.
        println("Lexing...")
        val tokenList = lexer.splitIntoTokens(input)
        val parser = createComposeParser()
        println("Parsing...")
        val astNode = parser.parse(InputContext(tokenList, 0))
        println("Interpreting...")
        return Interpreter().interpret(astNode.first)
    }
}

class AnalyzeCliFunction : CliFunction<Pair<String, File>, ReportResult> {
    override fun evaluate(input: Pair<String, File>): ReportResult {
        val astNode = createAstNode(input)
        val sca = FileToJsonAdapter().adapt(input.second)
        println("Analyzing...")
        // sca.verify(astNode.first), because of the new impl of sca
        return TODO()
    }
}

class FormatCliFunction : CliFunction<Pair<String, File>, String> {
    override fun evaluate(input: Pair<String, File>): String {
        println("Format function selected")
        val astNode = createAstNode(input)
        val formatter = Formatter()
        val rules = RuleParser().parseRulesFromFile(input.second.path)
        return formatter.format(astNode.first, rules).replace("\"\"", "\"")
    }
}

fun createAstNode(input: Pair<String, File>): Pair<ASTNode, Int> {
    val lexer = LexerDirector().createComposeLexer("1.0")
    println("Lexing...")
    val tokenList = lexer.splitIntoTokens(input.first)
    val parser = createComposeParser()
    println("Parsing...")
    return parser.parse(InputContext(tokenList, 0))
}
