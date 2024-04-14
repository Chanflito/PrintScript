package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.OperationResult


class IfStatementInterpreter (val node: AST, val context: Context) : Interpreter {
    override fun interpret(): InterpretResult {
        val ifStatement = node as IfStatement
        val conditionInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(node.condition, context)
        val conditionResult = conditionInterpreter.interpret() as InterpretResult.InterpretOperationResult
        if (conditionResult.operationResult !is OperationResult.BooleanResult) {
            throw RuntimeException("Condition must be a boolean")
        }
        val conditionValue= conditionResult.operationResult.value
        if (conditionValue) {
            val blockInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(ifStatement.ifBlock, context)
            return blockInterpreter.interpret()
        } else {
            return InterpretResult.EmptyResult()
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IfStatement
    }
}
