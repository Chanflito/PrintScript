package edu.austral.ingsis.gradle.interpreter.util

sealed class InterpreterResult {
    data class NumberResult(val value: Number) : InterpreterResult()
    data class StringResult(val value: String) : InterpreterResult()
    data class BooleanResult(val value: Boolean) : InterpreterResult()

    operator fun plus(other: InterpreterResult): InterpreterResult {
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

    operator fun minus(other: InterpreterResult): InterpreterResult {
        return if (this is NumberResult && other is NumberResult) {
            val result = this.value.toDouble() - other.value.toDouble()
            if (result.isInt()) NumberResult(result.toInt()) else NumberResult(result)
        } else {
            throw RuntimeException("Invalid operation for operands")
        }
    }

    operator fun times(other: InterpreterResult): InterpreterResult {
        return if (this is NumberResult && other is NumberResult) {
            val result = this.value.toDouble() * other.value.toDouble()
            if (result.isInt()) NumberResult(result.toInt()) else NumberResult(result)
        } else {
            throw RuntimeException("Invalid operation for operands")
        }
    }

    operator fun div(other: InterpreterResult): InterpreterResult {
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
}

