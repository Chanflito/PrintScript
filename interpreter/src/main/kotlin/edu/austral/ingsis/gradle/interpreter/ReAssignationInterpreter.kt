package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class ReAssignationInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as ReAssignationNode

        val identifier = node.identifierNode.name
        checkForErrors(context, identifier)
        val expressionInterpreter = interpreterManager.getWithTypeOrNot(node.expression, context.getVariableType(identifier))
        val expressionResult =
            expressionInterpreter.interpret(
                node.expression,
                context,
                interpreterManager,
            ) as InterpretResult.OperationResult
        val expression = expressionResult.value
        if (!context.getVariableType(identifier)?.let { doesTypeMatch(expression, it) }!!) throw RuntimeException("Type mismatch")
        return InterpretResult.ContextResult((Context(assignedVariables = mapOf(identifier to expression)))) // Return the new context
    }

    private fun checkForErrors(
        context: Context,
        identifier: String,
    ) {
        if (!context.isInContext(identifier)) {
            throw RuntimeException(
                "Variable $identifier not declared",
            )
        }
        if (context.isConstantAssigned(identifier)) {
            throw RuntimeException(
                "Variable $identifier is constant",
            )
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is ReAssignationNode
    }
}
