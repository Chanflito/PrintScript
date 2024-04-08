package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.Type
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class ReassignationInterpreter {
    fun interpret(
        reassignationNode: ReAssignationNode,
        variables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
        declaredVariables: HashMap<String, Type>,
    ): HashMap<String, Any> {
        val identifier = reassignationNode.identifierNode.name
        val expression = reassignationNode.expression
        if (!variables.containsKey(identifier) && !declaredVariables.containsKey(identifier)) {
            throw RuntimeException(
                "Variable $identifier not declared",
            )
        }
        if (constants.containsKey(identifier)) throw RuntimeException("Variable $identifier is constant")
        val result = ExpressionInterpreter().interpret(expression, variables, HashMap())
        if (!doesTypeMatch(result, declaredVariables[identifier])) throw RuntimeException("Type mismatch")
        variables[identifier] = result as Any
        return variables
    }
}
