package edu.austral.ingsis.gradle.interpreter.util

sealed class InterpretResult {
    data class ContextResult(val context: Context) : InterpretResult()

    data class OperationResult(val value: Any) : InterpretResult()
}
