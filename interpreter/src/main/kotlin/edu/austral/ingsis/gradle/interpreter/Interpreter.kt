package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.NodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

interface Interpreter {
    fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult

    /**
     * Checks if interpreter is made for interpreting the node passed as parameter
     */

    fun canInterpret(node: AST): Boolean

    // Some interpreters use nodeType, by default its null. This is used exclusively in InterpreterManager
    fun getNodeType(): NodeType? = null
}
