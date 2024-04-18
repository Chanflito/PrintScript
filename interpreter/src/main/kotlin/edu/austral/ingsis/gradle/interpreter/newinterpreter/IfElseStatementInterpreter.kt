package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
class IfElseStatementInterpreter () : Interpreter {
    override fun interpret(node: AST, context: Context, interpreterManager: InterpreterManager): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val ifStatement = node as IfElseStatement
        val conditionInterpreter = interpreterManager.getInterpreter(ifStatement.condition)
        val conditionResult = conditionInterpreter.interpret(node, context, interpreterManager) as InterpretResult.OperationResult
        if (conditionResult.value !is Boolean) {
            throw RuntimeException("Condition must be a boolean")
        }
        val conditionValue= conditionResult.value
        if (conditionValue) {
            val blockInterpreter = interpreterManager.getInterpreter(ifStatement.ifBlock)
            return blockInterpreter.interpret(ifStatement.ifBlock, context, interpreterManager)
        } else {
            val blockInterpreter = interpreterManager.getInterpreter(ifStatement.elseBlock)
            return blockInterpreter.interpret(ifStatement.elseBlock, context, interpreterManager)
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IfElseStatement
    }
}