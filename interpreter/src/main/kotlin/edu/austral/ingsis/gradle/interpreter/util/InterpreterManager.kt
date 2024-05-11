package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.NodeType
import edu.austral.ingsis.gradle.interpreter.Interpreter

/**
 * Provides utility functions related to the use of intepreters
 */
class InterpreterManager(
    val interpreters: List<Interpreter>,
    val printer: Printer,
    val envReader: EnvReader,
    val inputReader: InputReader,
) {
    /**
     * Returns interpreter based on type of AST passed and NodeType passed
     */
    fun getInterpreter(
        node: AST,
        type: NodeType? = null,
    ): Interpreter {
        return interpreters.find { it.canInterpret(node) && it.getNodeType() == type }
            ?: throw Exception("No interpreter found for node $node and type $type")
    }

    /**
     * Returns intepreter based only on type of AST
     */

    fun getInterpreterDisregardingType(node: AST): Interpreter {
        return interpreters.find { it.canInterpret(node) } ?: throw Exception("No interpreter found for $node")
    }

    /**
     * Tries to return interpreter based on AST and NodeType, if no corresponding NodeType can be found, returns disregarding NodeType
     */

    fun getWithTypeOrNot(
        node: AST,
        type: NodeType? = null,
    ): Interpreter {
        val intWithType = interpreters.find { it.canInterpret(node) && it.getNodeType() == type }
        if (intWithType != null) {
            return intWithType
        } else {
            return interpreters.find { it.canInterpret(node) } ?: throw Exception("No interpreter found for $node")
        }
    }
}
