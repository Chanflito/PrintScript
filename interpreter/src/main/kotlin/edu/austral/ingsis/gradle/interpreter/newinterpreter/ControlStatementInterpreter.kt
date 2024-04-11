package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.Type
import edu.austral.ingsis.gradle.interpreter.util.Context

class ControlStatementInterpreter: Interpreter<ControlStatement>{
    override fun interpret(
        node: ControlStatement,
        context: Context
    ): Context {
       return when (node) {
            is IfStatement -> {
                interpretIfStatement(node, context)
            }
            is IfElseStatement -> {
                interpretIfElseStatement(node, context)
            }
            else -> {
                throw RuntimeException("Control statement not recognized")
            }
        }
    }

    private fun interpretIfElseStatement(node: IfElseStatement, context: Context): Context{
        val condition = node.condition
        val newContext = ExpressionInterpreter().interpret(condition, context)
        val result = newContext.getLastBinaryOperationResult()
        if (result is Boolean) {
            if (result) {
                val blockNodeInterpreter = BlockNodeInterpreter()
                return blockNodeInterpreter.interpret(node.ifBlock, newContext)
            } else {
                val blockNodeInterpreter = BlockNodeInterpreter()
                return blockNodeInterpreter.interpret(node.elseBlock, newContext)
            }
        } else {
            throw RuntimeException("Condition must be a boolean")
        }
    }

    private fun interpretIfStatement(
        node: IfStatement,
        context: Context
    ): Context {
        val condition = node.condition
        val newContext = ExpressionInterpreter().interpret(condition, context)
        val result = newContext.getLastBinaryOperationResult()
        if (result is Boolean) {
            if (result) {
                val blockNodeInterpreter = BlockNodeInterpreter()
                return blockNodeInterpreter.interpret(node.ifBlock, newContext)
            }
            else {
                return newContext
            }
        } else {
            throw RuntimeException("Condition must be a boolean")
        }
    }
}
