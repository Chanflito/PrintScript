package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.interpreter.Interpreter

class InterpreterManager(
    val interpreters: List<Interpreter>,
    val printer: Printer,
    val envReader: EnvReader,
    val inputReader: InputReader,
) {
    fun getInterpreter(
        node: AST,
        type: NodeType? = null,
    ): Interpreter {
        return interpreters.find { it.canInterpret(node) && it.getNodeType() == type }
            ?: throw Exception("No interpreter found for node $node and type $type")
    }

    fun getInterpreterDisregardingType(node: AST): Interpreter {
        return interpreters.find { it.canInterpret(node) } ?: throw Exception("No interpreter found for $node")
    }
}
