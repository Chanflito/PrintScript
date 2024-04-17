package edu.austral.ingsis.gradle.cli

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.formatter.createDefaultFormatter
import edu.austral.ingsis.gradle.formatter.createDefaultRules
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.sca.ReportResult
import java.io.File

interface Function<in T, out O> {
    fun evaluate(input: T): O
}

// TODO wait interpreter to be refactored
class ExecuteFunction : Function<String, List<Any>> {
    override fun evaluate(input: String): List<Any> {
        /*val lexer = LexerDirector().createComposeLexer("1.1")
        val tokenList = lexer.splitIntoTokens(input)
        val parser = createComposeParser()
        val astNode = parser.parse(InputContext(tokenList, 0))
        return Interpreter().interpret(astNode.first)
    }*/
        TODO()
    }
}

// TODO - lucho
class AnalyzeFunction : Function<Pair<String, File>, ReportResult> {
    override fun evaluate(input: Pair<String, File>): ReportResult {
        /*val ast = createAstNode(input)
        val sca = FileToJsonAdapter().adapt(input.second)
        println("Analyzing...\n")
         */
        TODO()
    }
}

class FormatFunction : Function<Pair<String, File>, String> {
    override fun evaluate(input: Pair<String, File>): String {
        val ast = createAstNode(input)
        val formatter = createDefaultFormatter()
        val rules = ComposeRule(createDefaultRules())
        return formatter.format(ast.first, rules)
    }
}

// TODO wait parser to be refactored
private fun createAstNode(input: Pair<String, File>): Pair<AST, Int> {
    /*
    val lexer = LexerDirector().createComposeLexer("1.1")
    println("Lexing...")
    val tokenList = lexer.splitIntoTokens(input.first)

    val parser = createComposeParser()
    println("Parsing...")
    return parser.parse(InputContext(tokenList, 0))
     */
    TODO()
}
