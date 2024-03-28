package edu.austral.ingsis.gradle.sca.util

import edu.austral.ingsis.gradle.sca.CamelCaseReportErrorMessage
import edu.austral.ingsis.gradle.sca.ReportFailure
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.SnakeCaseReportErrorMessage

fun generateReport(reports: List<ReportResult>): ReportResult {
    val errors = reports.filterIsInstance<ReportFailure>()
    return if (errors.isEmpty()) ReportSuccess else ReportFailure(errors.joinToString { it.failureMessage })
}

val identifierRuleWithCustomErrorMap =
    mapOf(
        CamelCaseRule to CamelCaseReportErrorMessage(),
        SnakeCaseRule to SnakeCaseReportErrorMessage(),
    )
