package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult

class ProgramNodeInterpreter(val node: AST) : Interpreter {

    val context = Context()

    override fun interpret(): InterpretResult { // Create a new context for this interpretation
        interpretNode(node as ProgramNode)
        val interpretResult = InterpretResult.ContextResult(context)
        return interpretResult
    }

    private fun interpretNode(node: ProgramNode) {
        node.children.forEach { child ->
            val interpreterFactory = InterpreterFactory.internalGetInstance()
            val interpreter = interpreterFactory.createInterpreter<Interpreter>(child, context)
            val result = interpreter.interpret()
            // Update context based on the result if needed
            if (result is InterpretResult.ContextResult) {
                context.update(result.context)
            }
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is ProgramNode
    }
}
