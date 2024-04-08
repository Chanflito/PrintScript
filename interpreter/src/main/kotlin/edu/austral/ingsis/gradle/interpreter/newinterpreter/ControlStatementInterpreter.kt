package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.newast.Type

class ControlStatementInterpreter {
    fun interpret(
        node: ControlStatement,
        assignedVariables: HashMap<String, Any>,
        constants: HashMap<String, Any>,
        declaredVariables: HashMap<String, Type>,
    ) {
        TODO()
    }
}
