package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DivideNode
import edu.austral.ingsis.gradle.common.ast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.Operator
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.castToDesiredType

class SumInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as SumNode

        val (leftValue, rightValue) = interpretLeftAndRight(interpreterManager, node, context)
        return handleSum(leftValue, rightValue)
    }

    private fun handleSum(
        leftValue: Any,
        rightValue: Any,
    ): InterpretResult {
        return when {
            leftValue is String && rightValue is String ->
                InterpretResult.OperationResult(leftValue + rightValue)

            leftValue is Number && rightValue is Number ->
                InterpretResult.OperationResult(
                    castToDesiredType(leftValue.toDouble() + rightValue.toDouble()),
                )

            leftValue is String && rightValue is Number ->
                InterpretResult.OperationResult(
                    leftValue +
                        castToDesiredType(rightValue).toString(),
                )

            leftValue is Number && rightValue is String ->
                InterpretResult.OperationResult(
                    castToDesiredType(leftValue).toString() + rightValue,
                )

            else -> throw RuntimeException("Unsupported types for sum: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is SumNode
    }
}

class SubtractInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as SubtractNode

        val (leftValue, rightValue) = interpretLeftAndRight(interpreterManager, node, context)
        return handleSubtract(leftValue, rightValue)
    }

    private fun handleSubtract(
        leftValue: Any,
        rightValue: Any,
    ): InterpretResult {
        return when {
            leftValue is Number && rightValue is Number ->
                InterpretResult.OperationResult(
                    castToDesiredType(leftValue.toDouble() - rightValue.toDouble()),
                )

            else -> throw RuntimeException("Unsupported types for subtraction: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is SubtractNode
    }
}

class MultiplyInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as MultiplyNode

        val (leftValue, rightValue) = interpretLeftAndRight(interpreterManager, node, context)
        return handleMultiply(leftValue, rightValue)
    }

    private fun handleMultiply(
        leftValue: Any,
        rightValue: Any,
    ): InterpretResult {
        return when {
            leftValue is Number && rightValue is Number ->
                InterpretResult.OperationResult(
                    castToDesiredType(leftValue.toDouble() * rightValue.toDouble()),
                )

            else -> throw RuntimeException("Unsupported types for multiplication: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is MultiplyNode
    }
}

class DivideInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as DivideNode

        val (leftValue, rightValue) = interpretLeftAndRight(interpreterManager, node, context)
        return handleDivide(leftValue, rightValue)
    }

    private fun handleDivide(
        leftValue: Any,
        rightValue: Any,
    ): InterpretResult {
        return when {
            leftValue is Number && rightValue is Number ->
                InterpretResult.OperationResult(
                    castToDesiredType(leftValue.toDouble() / rightValue.toDouble()),
                )

            else -> throw RuntimeException("Unsupported types for division: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DivideNode
    }
}

private fun interpretLeftAndRight(
    interpreterManager: InterpreterManager,
    operatorNode: Operator,
    context: Context,
): Pair<Any, Any> {
    val leftInterpreter = interpreterManager.getInterpreterDisregardingType(operatorNode.left)
    val rightInterpreter = interpreterManager.getInterpreterDisregardingType(operatorNode.right)
    val leftResult =
        leftInterpreter.interpret(operatorNode.left, context, interpreterManager) as InterpretResult.OperationResult
    val rightResult =
        rightInterpreter.interpret(
            operatorNode.right,
            context,
            interpreterManager,
        ) as InterpretResult.OperationResult
    val leftValue = leftResult.value
    val rightValue = rightResult.value
    return Pair(leftValue, rightValue)
}
