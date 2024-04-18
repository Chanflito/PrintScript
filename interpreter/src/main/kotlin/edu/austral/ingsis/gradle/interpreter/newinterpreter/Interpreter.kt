package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

interface Interpreter {
    fun interpret(node: AST, context: Context, interpreterManager: InterpreterManager): InterpretResult

    fun canInterpret(node: AST): Boolean

    fun getType(): NodeType? = null

}


