package edu.austral.ingsis.gradle.sca.util

import edu.austral.ingsis.gradle.sca.ReportFailure
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess

fun generateReport(reports: List<ReportResult>): ReportResult {
    val errors = reports.filterIsInstance<ReportFailure>()
    return if (errors.isEmpty()) ReportSuccess else ReportFailure(errors.joinToString { it.failureMessage })
}