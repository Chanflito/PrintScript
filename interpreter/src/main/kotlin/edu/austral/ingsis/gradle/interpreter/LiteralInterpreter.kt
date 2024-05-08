package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.castToDesiredType

class StringLiteralInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (node !is StringLiteral) throw RuntimeException("Cannot interpret node $node")

        return InterpretResult.OperationResult(node.value)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is StringLiteral
    }
}

class NumberLiteralInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (node !is NumberLiteralNode) throw RuntimeException("Cannot interpret node $node")

        return InterpretResult.OperationResult(castToDesiredType(node.value))
    }

    override fun canInterpret(node: AST): Boolean {
        return node is NumberLiteralNode
    }
}

class BooleanLiteralInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (node !is BooleanLiteralNode) throw RuntimeException("Cannot interpret node $node")

        return InterpretResult.OperationResult(node.value)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is BooleanLiteralNode
    }
}
