package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.Literal
import edu.austral.ingsis.gradle.common.ast.newast.Operator
import edu.austral.ingsis.gradle.interpreter.util.Context

class ExpressionInterpreter: Interpreter<Expression>{

    override fun interpret(node: Expression, context: Context): Context {
        val result = interpretAux(node, context)
        if (result != null) {
            context.addBinaryOperationResult(result)
        }
        return context
    }
    private fun interpretAux(
        expression: Expression, context: Context
    ): Any? {
        return when (expression) {
            is Literal<*> -> expression.value
            is IdentifierNode -> resolveIdentifier(expression.name, context)
            is Operator -> evaluateOperation(expression, context)
            else -> throw RuntimeException("Expression not found")
        }
    }

    private fun resolveIdentifier(
        name: String,
        context: Context,
    ): Any {
        return context.getVariable(name) ?: context.isInContext(name)
        ?: throw RuntimeException("Variable $name with value assigned not found")
    }

    private fun evaluateOperation(
        expression: Operator,
        context: Context,
    ): Any {
        val left = interpretAux(expression.left, context)
        val right = interpretAux(expression.right, context)

        return when (expression.value) {
            "+" -> performAddition(left, right)
            "-" -> performSubtraction(left, right)
            "*" -> performMultiplication(left, right)
            "/" -> performDivision(left, right)
            else -> throw RuntimeException("Operator ${expression.value} not found")
        }
    }

    private fun performAddition(
        left: Any?,
        right: Any?,
    ): Any {
        return when {
            left is Int && right is Int -> left + right
            left is String && right is Int -> "$left$right"
            left is Int && right is String -> "$left$right"
            left is String && right is String -> "$left$right"
            else -> throw RuntimeException("Invalid operation for operands")
        }
    }

    private fun performSubtraction(
        left: Any?,
        right: Any?,
    ): Any {
        return if (left is Int && right is Int) left - right else throw RuntimeException("Invalid operation for operands")
    }

    private fun performMultiplication(
        left: Any?,
        right: Any?,
    ): Any {
        return if (left is Int && right is Int) left * right else throw RuntimeException("Invalid operation for operands")
    }

    private fun performDivision(
        left: Any?,
        right: Any?,
    ): Any {
        return if (left is Int && right is Int) left / right else throw RuntimeException("Invalid operation for operands")
    }
}
