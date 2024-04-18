package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class StringLiteralInterpreter(val type: NodeType = StringNodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val literalNode = node as StringLiteral
        return InterpretResult.OperationResult(literalNode.value)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is StringLiteral
    }

    override fun getNodeType(): NodeType {
        return type
    }
}

class NumberLiteralInterpreter(val type: NodeType = NumberNodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val literalNode = node as NumberLiteralNode
        return InterpretResult.OperationResult(literalNode.value)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is NumberLiteralNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}

class BooleanLiteralInterpreter(val type: NodeType = BooleanNodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val literalNode = node as BooleanLiteralNode
        return InterpretResult.OperationResult(literalNode.value)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is BooleanLiteralNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}
