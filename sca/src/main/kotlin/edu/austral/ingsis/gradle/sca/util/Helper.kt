package edu.austral.ingsis.gradle.sca.util

import edu.austral.ingsis.gradle.sca.ReportError
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess

fun generateReport(reports: List<ReportResult>): ReportResult {
    val errors = reports.filterIsInstance<ReportError>()
    return if (errors.isEmpty()) ReportSuccess else ReportError(errors.joinToString { it.error })
}