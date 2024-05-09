package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.IfStatement
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class IfStatementInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as IfStatement

        val conditionInterpreter = interpreterManager.getWithTypeOrNot(node.condition, BooleanNodeType)
        val conditionResult =
            conditionInterpreter.interpret(
                node.condition,
                context,
                interpreterManager,
            ) as InterpretResult.OperationResult

        if (conditionResult.value !is Boolean) {
            throw RuntimeException("Condition must be a boolean")
        }

        val conditionValue = conditionResult.value
        if (conditionValue) {
            val blockInterpreter = interpreterManager.getInterpreter(node.ifBlock)
            return blockInterpreter.interpret(node.ifBlock, context, interpreterManager)
        }
        return InterpretResult.OperationResult(Context())
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IfStatement
    }
}

class IfElseStatementInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as IfElseStatement

        val conditionInterpreter = interpreterManager.getWithTypeOrNot(node.condition, BooleanNodeType)
        val conditionResult =
            conditionInterpreter.interpret(
                node.condition,
                context,
                interpreterManager,
            ) as InterpretResult.OperationResult

        if (conditionResult.value !is Boolean) {
            throw RuntimeException("Condition must be a boolean")
        }

        val conditionValue = conditionResult.value
        if (conditionValue) {
            val blockInterpreter = interpreterManager.getInterpreter(node.ifBlock)
            return blockInterpreter.interpret(node.ifBlock, context, interpreterManager)
        } else {
            val blockInterpreter = interpreterManager.getInterpreter(node.elseBlock)
            return blockInterpreter.interpret(node.elseBlock, context, interpreterManager)
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IfElseStatement
    }
}
