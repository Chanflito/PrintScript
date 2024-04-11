package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.interpreter.util.Context

class PrintLnInterpreter: Interpreter<PrintLnNode>{
    override fun interpret(
        node: PrintLnNode,
        context: Context
    ): Context {
        val expression = node.expression
        val result = ExpressionInterpreter().interpret(expression, context)
        println(result)
        context.addPrintValue(result.toString())
        return context
    }
}
