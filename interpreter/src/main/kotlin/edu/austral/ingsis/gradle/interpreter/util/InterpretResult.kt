package edu.austral.ingsis.gradle.interpreter.util

sealed class InterpretResult{
    data class ContextResult(val context: Context): InterpretResult()
    data class OperationResult(val value: Any): InterpretResult()

    data class EmptyResult(val string: String = ""): InterpretResult()

    fun isContextResult(): Boolean = this is ContextResult
    fun isOperationResult(): Boolean = this is OperationResult
}

