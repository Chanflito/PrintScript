package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface ReportErrorMessage {
    fun build(tokenPosition: TokenPosition): String
}

class PrintlnReportErrorMessage : ReportErrorMessage {
    override fun build(tokenPosition: TokenPosition): String =
        "Linter ERROR: Expression is not allowed in println statement at line" +
            " ${getTokenEndRowPosition(tokenPosition)} column ${getTokenEndColumnPosition(tokenPosition)}"
}

class SnakeCaseReportErrorMessage : ReportErrorMessage { // How should i add the identifier name?
    override fun build(tokenPosition: TokenPosition): String =
        "Linter ERROR: Identifier is not in snake case " +
            "between  ${getTokenStartRowPosition(tokenPosition)} and ${getTokenEndRowPosition(tokenPosition)} line, " +
            "column ${getTokenStartColumnPosition(tokenPosition)} and ${getTokenEndColumnPosition(tokenPosition)}. "
}

class CamelCaseReportErrorMessage : ReportErrorMessage {
    override fun build(tokenPosition: TokenPosition): String =
        "Linter ERROR: Identifier is not in camel case " +
            "between  ${getTokenStartRowPosition(tokenPosition)} and ${getTokenEndRowPosition(tokenPosition)} line, " +
            "column ${getTokenStartColumnPosition(tokenPosition)} and ${getTokenEndColumnPosition(tokenPosition)}. "
}

fun getTokenEndRowPosition(tokenPosition: TokenPosition) = tokenPosition.endPosition.row

fun getTokenEndColumnPosition(tokenPosition: TokenPosition) = tokenPosition.endPosition.column

fun getTokenStartRowPosition(tokenPosition: TokenPosition) = tokenPosition.startPosition.row

fun getTokenStartColumnPosition(tokenPosition: TokenPosition) = tokenPosition.startPosition.column
