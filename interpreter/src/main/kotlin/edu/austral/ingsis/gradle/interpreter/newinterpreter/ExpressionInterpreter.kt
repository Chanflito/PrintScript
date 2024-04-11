package edu.austral.ingsis.gradle.interpreter.newinterpreter
import edu.austral.ingsis.gradle.common.ast.newast.*
import edu.austral.ingsis.gradle.common.ast.newast.Function
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpreterResult

class ExpressionInterpreter : Interpreter<Expression> {

    override fun interpret(node: Expression, context: Context): Context {
        if(node is Function) return interpretExpressionFunction(node, context)
        val result = interpretAux(node, context)
        context.addBinaryOperationResult(result)
        return context
    }

    private fun interpretAux(expression: Expression, context: Context): InterpreterResult {
        return when (expression) {
            is Literal<*> -> interpretLiteral(expression, context)
            is IdentifierNode -> resolveIdentifier(expression.name, context)
            is Operator -> evaluateOperation(expression, context)
            else -> throw RuntimeException("Expression not found")
        }
    }

    private fun interpretExpressionFunction(function: Function, context: Context): Context {
        return when (function) {
            is ReadInputNode -> ReadInputInterpreter().interpret(function, context)
            is ReadEnvNode -> ReadEnvInterpreter().interpret(function, context)
            else -> throw RuntimeException("Function not found")
        }
    }

    private fun interpretLiteral(literal: Literal<*>, context: Context): InterpreterResult {
        return when (literal) {
            is StringLiteral -> InterpreterResult.StringResult(literal.value)
            is BooleanLiteralNode -> InterpreterResult.BooleanResult(literal.value)
            is NumberLiteralNode -> InterpreterResult.NumberResult(literal.value)
            else -> throw RuntimeException("Unsupported literal type")
        }
    }

    private fun resolveIdentifier(name: String, context: Context): InterpreterResult {
        return context.getVariableOrConstant(name) ?: throw RuntimeException("Variable $name not found")
        }

    private fun evaluateOperation(expression: Operator, context: Context): InterpreterResult {
        val left = interpretAux(expression.left,context)
        val right = interpretAux(expression.right, context)
        return when (expression.operatorNodeType) {
            is SumOperatorNode -> left + right
            is SubtractOperatorNode -> left - right
            is MultiplyOperatorNode -> left * right
            is DivideOperatorNode -> left / right
            else -> throw RuntimeException("Operator not found")
        }
    }
}




