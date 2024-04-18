package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class BlockNodeInterpreter() : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val blockNode = node as BlockNode
        var newContext = context
        blockNode.statements.forEach { child ->
            val interpreter = interpreterManager.getInterpreter(child)
            val result = interpreter.interpret(child, context, interpreterManager)
            if (result is InterpretResult.ContextResult) {
                newContext = context.updateVariableValues(result.context)
            }
        }
        return InterpretResult.ContextResult(newContext)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is BlockNode
    }
}
