package edu.austral.ingsis.gradle.sca

interface Rule<T> {
    fun verify(node: T): ReportResult
}

sealed interface ReportResult

data object ReportSuccess : ReportResult

data class ReportFailure(val failureMessages: List<String>) : ReportResult
