package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class DeclarationAssignationInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")

        val interpretedNode = node as DeclarationAssignation
        val identifier = interpretedNode.identifierNode.name
        val expression = interpretedNode.expression
        val keyword = interpretedNode.keyword.value
        val type = interpretedNode.nodeType

        checkIfVariableCanBeDeclared(identifier, context)
        val result = interpretExpression(expression, type, context, interpreterManager)
        checkTypeMatch(result, type)

        val newContext = createContextAfterDeclaration(identifier, result, type, keyword)
        return InterpretResult.ContextResult(newContext)
    }

    private fun checkIfVariableCanBeDeclared(
        identifier: String,
        context: Context,
    ) {
        if (context.isInContext(identifier)) {
            throw RuntimeException("Variable $identifier already declared")
        }
    }

    private fun interpretExpression(
        expression: AST,
        type: NodeType,
        context: Context,
        interpreterManager: InterpreterManager,
    ): Any {
        val expressionInterpreter = interpreterManager.getInterpreter(expression, type)
        val interpretResult = expressionInterpreter.interpret(expression, context, interpreterManager) as InterpretResult.OperationResult
        return interpretResult.value
    }

    private fun checkTypeMatch(
        result: Any,
        type: NodeType,
    ) {
        if (!doesTypeMatch(result, type)) {
            throw RuntimeException("Type mismatch")
        }
    }

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

        return Context(assignedVariables = assignedMap, declaredVariables = mapOf(identifier to type), constants = constMap)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DeclarationAssignation
    }
}
