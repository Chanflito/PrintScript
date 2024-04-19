package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.BlockNodeInterpreter
import edu.austral.ingsis.gradle.interpreter.BooleanLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.DeclarationAssignationInterpreter
import edu.austral.ingsis.gradle.interpreter.DeclarationInterpreter
import edu.austral.ingsis.gradle.interpreter.DivideInterpreter
import edu.austral.ingsis.gradle.interpreter.IdentifierInterpreter
import edu.austral.ingsis.gradle.interpreter.IfElseStatementInterpreter
import edu.austral.ingsis.gradle.interpreter.IfStatementInterpreter
import edu.austral.ingsis.gradle.interpreter.MultiplyInterpreter
import edu.austral.ingsis.gradle.interpreter.NumberLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.PrintLnInterpreter
import edu.austral.ingsis.gradle.interpreter.ReadEnvInterpreter
import edu.austral.ingsis.gradle.interpreter.ReadInputInterpreter
import edu.austral.ingsis.gradle.interpreter.ReassignationInterpreter
import edu.austral.ingsis.gradle.interpreter.StringLiteralInterpreter
import edu.austral.ingsis.gradle.interpreter.SubtractInterpreter
import edu.austral.ingsis.gradle.interpreter.SumInterpreter
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.KotlinEnvReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinInputReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinPrinter
import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import edu.austral.ingsis.gradle.lexer.iterator.LexerIterator
import edu.austral.ingsis.gradle.parser.iterator.ParserIterator
import edu.austral.ingsis.gradle.parser.util.createComposeParser

fun execute(input: String) {
    // val input = "const b: string = \"this should be valid in 1.1\";"
    val lexer = LexerDirector().createComposeLexer("1.1")
    val lexerIterator = LexerIterator(lexer, input.byteInputStream())
    val parserIterator = ParserIterator(lexerIterator, createComposeParser())
    var context = Context()
    val interpreters =
        listOf(
            BlockNodeInterpreter(),
            DeclarationInterpreter(),
            StringLiteralInterpreter(),
            NumberLiteralInterpreter(),
            BooleanLiteralInterpreter(),
            SumInterpreter(),
            SubtractInterpreter(),
            MultiplyInterpreter(),
            DivideInterpreter(),
            DeclarationInterpreter(),
            DeclarationAssignationInterpreter(),
            IdentifierInterpreter(),
            IfElseStatementInterpreter(),
            IfStatementInterpreter(),
            PrintLnInterpreter(),
            ReadEnvInterpreter(StringNodeType),
            ReadEnvInterpreter(NumberNodeType),
            ReadEnvInterpreter(BooleanNodeType),
            ReadInputInterpreter(StringNodeType),
            ReadInputInterpreter(NumberNodeType),
            ReadInputInterpreter(BooleanNodeType),
            ReassignationInterpreter(),
        )
    try {
        while (parserIterator.hasNext()) {
            val ast = parserIterator.next()
            val interpreterManager =
                InterpreterManager(
                    interpreters,
                    KotlinPrinter(),
                    KotlinEnvReader(),
                    KotlinInputReader(),
                )
            val interpreter = ast?.let { interpreterManager.getInterpreter(it, null) }
            val interpretResult = interpreter?.interpret(ast, context, interpreterManager)
            if (interpretResult is InterpretResult.ContextResult) {
                context = context.update(interpretResult.context)
            }
        }
    } catch (e: Exception) {
        println(e.message)
    }
}
