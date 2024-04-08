package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode

class PrintLnInterpreter {
    fun interpret(
        printLnNode: PrintLnNode,
        variables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
    ): String {
        val expression = printLnNode.expression
        val result = ExpressionInterpreter().interpret(expression, variables, constants)
        println(result)
        return result.toString()
    }
}
