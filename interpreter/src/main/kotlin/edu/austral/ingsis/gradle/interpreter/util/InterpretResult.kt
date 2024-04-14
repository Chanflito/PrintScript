package edu.austral.ingsis.gradle.interpreter.util

sealed class OperationResult {
    data class NumberResult(val value: Number) : OperationResult()
    data class StringResult(val value: String) : OperationResult()
    data class BooleanResult(val value: Boolean) : OperationResult()

    operator fun plus(other: OperationResult): OperationResult {
        return when {
            this is NumberResult && other is NumberResult -> {
                val result = this.value.toDouble() + other.value.toDouble()
                if (result.isInt()) NumberResult(result.toInt()) else NumberResult(result)
            }
            this is StringResult && other is StringResult ->
                StringResult(this.value + other.value)
            this is StringResult && other is NumberResult ->
                StringResult(this.value + other.value.toDouble())
            this is NumberResult && other is StringResult ->
                StringResult(this.value.toString() + other.value)
            else -> throw RuntimeException("Invalid operation for operands")
        }
    }

    operator fun minus(other: OperationResult): OperationResult {
        return if (this is NumberResult && other is NumberResult) {
            val result = this.value.toDouble() - other.value.toDouble()
            if (result.isInt()) NumberResult(result.toInt()) else NumberResult(result)
        } else {
            throw RuntimeException("Invalid operation for operands")
        }
    }

    operator fun times(other: OperationResult): OperationResult {
        return if (this is NumberResult && other is NumberResult) {
            val result = this.value.toDouble() * other.value.toDouble()
            if (result.isInt()) NumberResult(result.toInt()) else NumberResult(result)
        } else {
            throw RuntimeException("Invalid operation for operands")
        }
    }

    operator fun div(other: OperationResult): OperationResult {
        return if (this is NumberResult && other is NumberResult && other.value.toDouble() != 0.0) {
            val result = this.value.toDouble() / other.value.toDouble()
            if (result.isInt()) NumberResult(result.toInt()) else NumberResult(result)
        } else {
            throw RuntimeException("Invalid operation for operands")
        }
    }

    private fun Double.isInt(): Boolean {
        return this % 1 == 0.0
    }

    fun printValue(): String {
        return when (this) {
            is NumberResult -> value.toString()
            is StringResult -> value
            is BooleanResult -> value.toString()
        }
    }

    fun getValue(): Any {
        return when (this) {
            is NumberResult -> value
            is StringResult -> value
            is BooleanResult -> value
        }
    }
}

sealed class InterpretResult{
    data class ContextResult(val context: Context): InterpretResult()
    data class InterpretOperationResult(val operationResult:OperationResult): InterpretResult()

    data class EmptyResult(val string: String = ""): InterpretResult()

    fun isContextResult(): Boolean = this is ContextResult
    fun isOperationResult(): Boolean = this is InterpretOperationResult
}

