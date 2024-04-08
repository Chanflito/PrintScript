package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.Type

class DeclarationInterpreter {
    fun interpret(
        declarationNode: DeclarationNode,
        variables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
        declaredVariables: HashMap<String, Type>,
    ): HashMap<String, Type> {
        val type = declarationNode.type
        val name = declarationNode.identifierNode.name
        if (variables.containsKey(name) || constants.containsKey(name) || declaredVariables.containsKey(name)) {
            throw RuntimeException("Variable $name already declared")
        }
        declaredVariables[name] = type
        return declaredVariables
    }
}
