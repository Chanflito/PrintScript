package edu.austral.ingsis.gradle.interpreter.newinterpreter
import edu.austral.ingsis.gradle.common.ast.newast.*
import edu.austral.ingsis.gradle.common.ast.newast.Function
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.OperationResult

class ExpressionInterpreter (val node: AST,val context: Context, val type: NodeType) : Interpreter {

    override fun interpret(): InterpretResult {
        val expression = node as Expression
        val result = interpretNode(expression)
        return InterpretResult.InterpretOperationResult(result)
    }

    private fun interpretNode(expression: Expression): OperationResult {
        return when (expression) {
            is Literal<*> -> interpretLiteral(expression)
            is IdentifierNode -> resolveIdentifier(expression.name)
            is Operator -> evaluateOperation(expression)
            else -> throw RuntimeException("Expression not found")
        }
    }

    private fun interpretLiteral(literal: Literal<*>): OperationResult {
        return when (literal) {
            is StringLiteral -> OperationResult.StringResult(literal.value)
            is BooleanLiteralNode -> OperationResult.BooleanResult(literal.value)
            is NumberLiteralNode -> OperationResult.NumberResult(literal.value)
            else -> throw RuntimeException("Unsupported literal type")
        }
    }

    private fun resolveIdentifier(name: String): OperationResult {
        return context.getVariableOrConstant(name) ?: throw RuntimeException("Variable $name not found")
        }

    private fun evaluateOperation(expression: Operator): OperationResult {
        val operatorInterpreter= InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(expression, context)
        val interpretOperationResult= operatorInterpreter.interpret() as InterpretResult.InterpretOperationResult
        return interpretOperationResult.operationResult
    }

    override fun canInterpret(node: AST): Boolean {
        return node is Expression && node !is Function
    }
}




