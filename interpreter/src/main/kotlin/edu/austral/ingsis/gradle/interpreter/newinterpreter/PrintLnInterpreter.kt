package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpreterResult

class PrintLnInterpreter: Interpreter<PrintLnNode>{
    override fun interpret(
        node: PrintLnNode,
        context: Context
    ): Context {
        val expression = node.expression
        val newContext = ExpressionInterpreter().interpret(expression, context)
        when(val result = newContext.getLastBinaryOperationResult()){
            is InterpreterResult.StringResult -> {
                println(result.value)
                newContext.addPrintValue(result.value)
            }
            is InterpreterResult.NumberResult -> {
                println(result.value)
                newContext.addPrintValue(result.value.toString())
            }
            is InterpreterResult.BooleanResult -> {
                println(result.value)
                newContext.addPrintValue(result.value.toString())
            }
            else -> {
                throw RuntimeException("Type not recognized")
            }
        }
        return newContext
    }
}
