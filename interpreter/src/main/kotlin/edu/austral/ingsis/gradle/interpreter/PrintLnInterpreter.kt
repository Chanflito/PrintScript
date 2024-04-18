package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class PrintLnInterpreter() : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val printLnNode = node as PrintLnNode
        val expression = printLnNode.expression
        val interpreter = interpreterManager.getInterpreter(expression)
        val interpreterResult = interpreter.interpret(expression, context, interpreterManager) as InterpretResult.OperationResult
        val resultValue = interpreterResult.value
        interpreterManager.printer.print(resultValue.toString())
        return InterpretResult.ContextResult(Context(printValues = listOf(resultValue.toString())))
    }

    override fun canInterpret(node: AST): Boolean {
        return node is PrintLnNode
    }
}
