package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.Operator
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.OperationResult

class SumInterpreter(val node:AST, val context: Context): Interpreter {

    override fun interpret(): InterpretResult {
        val operatorNode = node as SumNode
        val operationResult = interpretNode(operatorNode)
        return InterpretResult.InterpretOperationResult(operationResult)
    }

    private fun interpretNode(node: Operator) : OperationResult {
        val left = node.left
        val right = node.right
        val leftInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(left, context)
        val rightInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(right, context)
        val leftResult = leftInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val rightResult = rightInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val leftValue = leftResult.operationResult
        val rightValue = rightResult.operationResult
        return leftValue + rightValue
    }

    override fun canInterpret(node: AST): Boolean {
        return node is SumNode
    }
}

class SubtractInterpreter(val node:AST, val context: Context): Interpreter {

    override fun interpret(): InterpretResult {
        val operatorNode = node as SubtractNode
        val operationResult = interpretNode(operatorNode)
        return InterpretResult.InterpretOperationResult(operationResult)
    }

    private fun interpretNode(node: Operator) : OperationResult {
        val left = node.left
        val right = node.right
        val leftInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(left, context)
        val rightInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(right, context)
        val leftResult = leftInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val rightResult = rightInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val leftValue = leftResult.operationResult
        val rightValue = rightResult.operationResult
        return leftValue - rightValue
    }

    override fun canInterpret(node: AST): Boolean {
        return node is SubtractNode
    }
}

class MultiplyInterpreter(val node:AST, val context: Context): Interpreter {

    override fun interpret(): InterpretResult {
        val operatorNode = node as SumNode
        val operationResult = interpretNode(operatorNode)
        return InterpretResult.InterpretOperationResult(operationResult)
    }

    private fun interpretNode(node: Operator) : OperationResult {
        val left = node.left
        val right = node.right
        val leftInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(left, context)
        val rightInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(right, context)
        val leftResult = leftInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val rightResult = rightInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val leftValue = leftResult.operationResult
        val rightValue = rightResult.operationResult
        return leftValue * rightValue
    }

    override fun canInterpret(node: AST): Boolean {
        return node is MultiplyNode
    }
}

class DivideInterpreter(val node:AST, val context: Context): Interpreter {

    override fun interpret(): InterpretResult {
        val operatorNode = node as SumNode
        val operationResult = interpretNode(operatorNode)
        return InterpretResult.InterpretOperationResult(operationResult)
    }

    private fun interpretNode(node: Operator) : OperationResult {
        val left = node.left
        val right = node.right
        val leftInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(left, context)
        val rightInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(right, context)
        val leftResult = leftInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val rightResult = rightInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val leftValue = leftResult.operationResult
        val rightValue = rightResult.operationResult
        return leftValue / rightValue
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DivideNode
    }
}