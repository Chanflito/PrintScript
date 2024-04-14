package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.OperationResult

class PrintLnInterpreter (val node: AST, val context: Context): Interpreter{
    override fun interpret(): InterpretResult {
        val printLnNode = node as PrintLnNode
        val newContext = interpretNode(printLnNode)
        return InterpretResult.ContextResult(context)
    }

    private fun interpretNode(node: PrintLnNode) {
        val expression = node.expression
        val interpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(expression, context)
        val interpreterResult = interpreter.interpret() as InterpretResult.InterpretOperationResult
        when(val result = interpreterResult.operationResult){
            is OperationResult.StringResult -> {
                println(result.value)
                context.addPrintValue(result.value)
            }
            is OperationResult.NumberResult -> {
                println(result.value)
                context.addPrintValue(result.value.toString())
            }
            is OperationResult.BooleanResult -> {
                println(result.value)
                context.addPrintValue(result.value.toString())
            }
            else -> {
                throw RuntimeException("Type not recognized")
            }
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is PrintLnNode
    }
}
