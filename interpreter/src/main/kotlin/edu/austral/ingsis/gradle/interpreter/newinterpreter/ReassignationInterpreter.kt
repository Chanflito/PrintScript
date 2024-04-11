package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class ReassignationInterpreter : Interpreter<ReAssignationNode> {
    override fun interpret(
        node: ReAssignationNode,
        context: Context
    ): Context {
        val identifier = node.identifierNode.name
        val expression = node.expression
        if (!context.isVariableDeclared(identifier)) {
            throw RuntimeException(
                "Variable $identifier not declared",
            )
        }
        if (context.isConstantAssigned(identifier)) throw RuntimeException("Variable $identifier is constant")
        val newContext= ExpressionInterpreter().interpret(expression, context)
        val result = newContext.getLastBinaryOperationResult()
        if (!doesTypeMatch(result, context.getVariableType(identifier))) throw RuntimeException("Type mismatch")
        newContext.assignVariable(identifier, result)
        return newContext
    }
}
