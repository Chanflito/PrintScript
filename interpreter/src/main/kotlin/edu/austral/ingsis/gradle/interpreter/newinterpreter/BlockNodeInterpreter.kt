package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult

class BlockNodeInterpreter(val node: AST, val context: Context): Interpreter {
    override fun interpret(): InterpretResult {
        interpretNode(node as BlockNode)
        val interpretResult = InterpretResult.ContextResult(context)
        return interpretResult
    }

    private fun interpretNode(node: BlockNode) {
        node.statements.forEach { child ->
            val interpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(child, context)
            val result = interpreter.interpret()
            // Update context based on the result if needed
            if (result is InterpretResult.ContextResult) {
                context.update(result.context)
            }
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is BlockNode
    }
}
