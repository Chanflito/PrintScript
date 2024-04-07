package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.token.Token

interface ReportErrorMessage {
    fun build(token: Token?): String
}

class PrintlnReportErrorMessage : ReportErrorMessage {
    override fun build(token: Token?): String =
        "Linter ERROR: Expression is not allowed in println statement at line" +
            " ${getTokenEndRowPosition(token)} column ${getTokenEndColumnPosition(token)}"
}

class SnakeCaseReportErrorMessage : ReportErrorMessage {
    override fun build(token: Token?): String =
        "Linter ERROR: Identifier ${token?.value} is not in snake case " +
            "between  ${getTokenStartRowPosition(token)} and ${getTokenEndRowPosition(token)} line, " +
            "column ${getTokenStartColumnPosition(token)} and ${getTokenEndColumnPosition(token)}. "
}

class CamelCaseReportErrorMessage : ReportErrorMessage {
    override fun build(token: Token?): String =
        "Linter ERROR: Identifier ${token?.value} is not in camel case " +
            "between  ${getTokenStartRowPosition(token)} and ${getTokenEndRowPosition(token)} line, " +
            "column ${getTokenStartColumnPosition(token)} and ${getTokenEndColumnPosition(token)}. "
}

fun getTokenEndRowPosition(token: Token?) = token?.tokenPosition?.endPosition?.row

fun getTokenEndColumnPosition(token: Token?) = token?.tokenPosition?.endPosition?.column

fun getTokenStartRowPosition(token: Token?) = token?.tokenPosition?.startPosition?.row

fun getTokenStartColumnPosition(token: Token?) = token?.tokenPosition?.startPosition?.column
