package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class IfStatementInterpreter() : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val ifStatement = node as IfStatement
        val conditionInterpreter = interpreterManager.getInterpreter(ifStatement.condition, BooleanNodeType)
        val conditionResult =
            conditionInterpreter.interpret(
                ifStatement.condition,
                context,
                interpreterManager,
            ) as InterpretResult.OperationResult
        if (conditionResult.value !is Boolean) {
            throw RuntimeException("Condition must be a boolean")
        }
        val conditionValue = conditionResult.value
        if (conditionValue) {
            val blockInterpreter = interpreterManager.getInterpreter(ifStatement.ifBlock)
            return blockInterpreter.interpret(ifStatement.ifBlock, context, interpreterManager)
        }
        return InterpretResult.OperationResult(Context())
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IfStatement
    }
}
