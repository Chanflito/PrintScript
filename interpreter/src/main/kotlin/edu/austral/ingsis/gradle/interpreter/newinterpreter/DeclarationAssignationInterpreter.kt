package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.Type
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class DeclarationAssignationInterpreter {
    fun interpret(
        declarationAssignationNode: DeclarationAssignation,
        variables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
        declaredVariables: HashMap<String, Type>,
    ): Triple<HashMap<String, Any>, HashMap<String, Any>, HashMap<String, Type>> {
        val identifier = declarationAssignationNode.identifierNode.name
        val expression = declarationAssignationNode.expression
        val keyword = declarationAssignationNode.keyword.value
        val type = declarationAssignationNode.type
        if (variables.containsKey(identifier) || constants.containsKey(identifier) || declaredVariables.containsKey(identifier)) {
            throw RuntimeException(
                "Variable $identifier already declared",
            )
        }
        val result = ExpressionInterpreter().interpret(expression, variables, constants)
        if (!doesTypeMatch(result, type)) throw RuntimeException("Type mismatch")
        when (keyword) {
            "let" -> {
                variables[identifier] = result as Any
            }
            "const" -> {
                constants[identifier] = result as Any
            }
        }
        declaredVariables[identifier] = type
        return Triple(variables, constants, declaredVariables)
    }
}
