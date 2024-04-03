package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.token.Token

interface ReportErrorMessage {
    fun build(token: Token?): String
}

class PrintlnReportErrorMessage : ReportErrorMessage {
    override fun build(token: Token?): String =
        "Linter ERROR: Expression is not allowed in println statement at line" +
            " ${token?.endPosition?.row} column ${token?.endPosition?.column}"
}

class SnakeCaseReportErrorMessage : ReportErrorMessage {
    override fun build(token: Token?): String =
        "Linter ERROR: Identifier ${token?.value} is not in snake case " +
            "between  ${token?.startPosition?.row} and ${token?.endPosition?.row} line, " +
            "column ${token?.startPosition?.column} and ${token?.endPosition?.column}. "
}

class CamelCaseReportErrorMessage : ReportErrorMessage {
    override fun build(token: Token?): String =
        "Linter ERROR: Identifier ${token?.value} is not in camel case " +
            "between  ${token?.startPosition?.row} and ${token?.endPosition?.row} line, " +
            "column ${token?.startPosition?.column} and ${token?.endPosition?.column}. "
}
