package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class DeclarationAssignationInterpreter: Interpreter<DeclarationAssignation> {
    override fun interpret(
        node: DeclarationAssignation, context: Context): Context {
        val identifier = node.identifierNode.name
        val expression = node.expression
        val keyword = node.keyword.value
        val type = node.type
        if (context.isInContext(identifier)) {
            throw RuntimeException(
                "Variable $identifier already declared",
            )
        }
        val newContext = ExpressionInterpreter().interpret(expression, context)
        val result = newContext.getLastBinaryOperationResult()
        if (!doesTypeMatch(result, type)) throw RuntimeException("Type mismatch")
        when (keyword) {
            "let" -> {
                newContext.assignVariable(identifier, result as Any)
            }
            "const" -> {
                newContext.assignConstant(identifier, result as Any)
            }
        }
        newContext.declareVariable(identifier, type)
        return newContext
    }
}
