package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.interpreter.util.Context

interface Interpreter<T: AST> {
    fun interpret(node: T, context: Context): Context
}