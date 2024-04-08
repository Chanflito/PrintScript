package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.Literal
import edu.austral.ingsis.gradle.common.ast.newast.Operator

class ExpressionInterpreter {
    fun interpret(
        expression: Expression,
        variables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
    ): Any? {
        return when (expression) {
            is Literal<*> -> expression.value
            is IdentifierNode -> resolveIdentifier(expression.name, variables, constants)
            is Operator -> evaluateOperation(expression, variables, constants)
            else -> throw RuntimeException("Expression not found")
        }
    }

    private fun resolveIdentifier(
        name: String,
        variables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
    ): Any? {
        return variables[name] ?: constants[name] ?: throw RuntimeException("Variable $name not found")
    }

    private fun evaluateOperation(
        expression: Operator,
        variables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
    ): Any {
        val left = interpret(expression.left, variables, constants)
        val right = interpret(expression.right, variables, constants)

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
