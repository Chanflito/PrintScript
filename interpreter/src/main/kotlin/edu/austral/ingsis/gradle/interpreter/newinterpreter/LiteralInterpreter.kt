package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.OperationResult

class StringLiteralInterpreter(val node: AST, val context: Context): Interpreter {

    override fun interpret(): InterpretResult {
        val literalNode = node as StringLiteral
        return InterpretResult.InterpretOperationResult(OperationResult.StringResult(literalNode.value))
    }

    override fun canInterpret(node: AST): Boolean {
        return node is StringLiteral
    }
}

class NumberLiteralInterpreter(val node: AST, val context: Context): Interpreter {

    override fun interpret(): InterpretResult {
        val literalNode = node as NumberLiteralNode
        return InterpretResult.InterpretOperationResult(OperationResult.NumberResult(literalNode.value))
    }

    override fun canInterpret(node: AST): Boolean {
        return node is NumberLiteralNode
    }
}

class BooleanLiteralInterpreter(val node: AST, val context: Context): Interpreter {

    override fun interpret(): InterpretResult {
        val literalNode = node as BooleanLiteralNode
        return InterpretResult.InterpretOperationResult(OperationResult.BooleanResult(literalNode.value))
    }

    override fun canInterpret(node: AST): Boolean {
        return node is BooleanLiteralNode
    }
}