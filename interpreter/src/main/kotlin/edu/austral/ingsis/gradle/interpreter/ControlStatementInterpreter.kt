package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class IfStatementInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (node !is IfStatement) throw RuntimeException("Cannot interpret node $node")

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
        if (node !is IfElseStatement) throw RuntimeException("Cannot interpret node $node")

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
