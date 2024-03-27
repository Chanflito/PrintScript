package edu.austral.ingsis.gradle.sca

interface Rule<T> {
    fun verify(node : T) : ReportResult
}

sealed interface ReportResult

data object ReportSuccess : ReportResult

data class ReportError(val error: String) : ReportResult

interface ReportErrorMessage{ // Implement this before
    override fun toString(): String
}

