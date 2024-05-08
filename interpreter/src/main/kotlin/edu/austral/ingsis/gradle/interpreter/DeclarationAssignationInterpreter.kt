package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

/**
 * Interpreter for when variables or constants are declared and have a value assigned in the same line
 */

class DeclarationAssignationInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (node !is DeclarationAssignation) throw RuntimeException("Cannot interpret node $node")

        val identifier = node.identifierNode.name
        val expression = node.expression
        val keyword = node.keyword.value
        val type = node.nodeType

        checkIfVariableCanBeDeclared(identifier, context)
        val result = interpretExpression(expression, type, context, interpreterManager)
        checkTypeMatch(result, type)

        val newContext = createContextAfterDeclaration(identifier, result, type, keyword)
        return InterpretResult.ContextResult(newContext)
    }

    /**
     * Checks if variables isn't already declared
     */

    private fun checkIfVariableCanBeDeclared(
        identifier: String,
        context: Context,
    ) {
        if (context.isInContext(identifier)) {
            throw RuntimeException("Variable $identifier already declared")
        }
    }

    /**
     * Returns result of interpreting expression on the right-hand side of the assignation
     */
    private fun interpretExpression(
        expression: AST,
        type: NodeType,
        context: Context,
        interpreterManager: InterpreterManager,
    ): Any {
        val expressionInterpreter = interpreterManager.getWithTypeOrNot(expression, type)
        val interpretResult = expressionInterpreter.interpret(expression, context, interpreterManager) as InterpretResult.OperationResult
        return interpretResult.value
    }

    /**
     * Uses utility function to check if the declared NodeType matches the returning type of interpreting the expression
     */
    private fun checkTypeMatch(
        result: Any,
        type: NodeType,
    ) {
        if (!doesTypeMatch(result, type)) {
            throw RuntimeException("Type mismatch")
        }
    }

    /**
     * Returns a new context with the declared variable or constant
     */

    private fun createContextAfterDeclaration(
        identifier: String,
        result: Any,
        type: NodeType,
        keyword: String,
    ): Context {
        val assignedMap =
            if (keyword == "let") {
                mapOf(identifier to result)
            } else {
                emptyMap()
            }

        val constMap =
            if (keyword == "const") {
                mapOf(identifier to result)
            } else {
                emptyMap()
            }

        return Context(assignedVariables = assignedMap, declaredVariablesAndConstants = mapOf(identifier to type), constants = constMap)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DeclarationAssignation
    }
}
