package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

/**
 * Interpreter for blocks inside control structures like ifs, or loops.
 */

class BlockNodeInterpreter() : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val blockNode = node as BlockNode
        var newContext = context // this context will be used to update the outer scope
        blockNode.children.forEach { child ->
            val interpreter = interpreterManager.getInterpreter(child)
            val result = interpreter.interpret(child, context, interpreterManager)
            if (result is InterpretResult.ContextResult) {
                // Only changes to variables declared in the outer scope are brought back to it
                newContext = context.updateVariableValues(result.context)
            }
        }
        return InterpretResult.ContextResult(newContext)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is BlockNode
    }
}
