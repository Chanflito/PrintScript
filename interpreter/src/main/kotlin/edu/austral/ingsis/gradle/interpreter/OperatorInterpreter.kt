package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class SumInterpreter(val type: NodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val operatorNode = node as SumNode
        val leftInterpreter = interpreterManager.getInterpreter(operatorNode.left, type)
        val rightInterpreter = interpreterManager.getInterpreter(operatorNode.right, type)
        val leftResult = leftInterpreter.interpret(operatorNode.left, context, interpreterManager) as InterpretResult.OperationResult
        val rightResult = rightInterpreter.interpret(operatorNode.right, context, interpreterManager) as InterpretResult.OperationResult
        val leftValue = leftResult.value
        val rightValue = rightResult.value

        return when {
            leftValue is String && rightValue is String -> InterpretResult.OperationResult(leftValue + rightValue)
            leftValue is Int && rightValue is Int -> InterpretResult.OperationResult(leftValue + rightValue)
            leftValue is String && rightValue is Int -> InterpretResult.OperationResult(leftValue + rightValue.toString())
            leftValue is Int && rightValue is String -> InterpretResult.OperationResult(leftValue.toString() + rightValue)
            else -> throw RuntimeException("Unsupported types for sum: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is SumNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}

class SubtractInterpreter(val type: NodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val operatorNode = node as SubtractNode
        val leftInterpreter = interpreterManager.getInterpreter(operatorNode.left, type)
        val rightInterpreter = interpreterManager.getInterpreter(operatorNode.right, type)
        val leftResult = leftInterpreter.interpret(operatorNode.left, context, interpreterManager) as InterpretResult.OperationResult
        val rightResult = rightInterpreter.interpret(operatorNode.right, context, interpreterManager) as InterpretResult.OperationResult
        val leftValue = leftResult.value
        val rightValue = rightResult.value

        return when {
            leftValue is Int && rightValue is Int -> InterpretResult.OperationResult(leftValue - rightValue)
            else -> throw RuntimeException("Unsupported types for subtraction: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is SubtractNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}

class MultiplyInterpreter(val type: NodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val operatorNode = node as MultiplyNode
        val leftInterpreter = interpreterManager.getInterpreter(operatorNode.left, type)
        val rightInterpreter = interpreterManager.getInterpreter(operatorNode.right, type)
        val leftResult = leftInterpreter.interpret(operatorNode.left, context, interpreterManager) as InterpretResult.OperationResult
        val rightResult = rightInterpreter.interpret(operatorNode.right, context, interpreterManager) as InterpretResult.OperationResult
        val leftValue = leftResult.value
        val rightValue = rightResult.value

        return when {
            leftValue is Int && rightValue is Int -> InterpretResult.OperationResult(leftValue * rightValue)
            else -> throw RuntimeException("Unsupported types for multiplication: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is MultiplyNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}

class DivideInterpreter(val type: NodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val operatorNode = node as DivideNode
        val leftInterpreter = interpreterManager.getInterpreter(operatorNode.left, type)
        val rightInterpreter = interpreterManager.getInterpreter(operatorNode.right, type)
        val leftResult = leftInterpreter.interpret(operatorNode.left, context, interpreterManager) as InterpretResult.OperationResult
        val rightResult = rightInterpreter.interpret(operatorNode.right, context, interpreterManager) as InterpretResult.OperationResult
        val leftValue = leftResult.value
        val rightValue = rightResult.value

        return when {
            leftValue is Int && rightValue is Int -> InterpretResult.OperationResult(leftValue / rightValue)
            else -> throw RuntimeException("Unsupported types for division: $leftValue and $rightValue")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DivideNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}
