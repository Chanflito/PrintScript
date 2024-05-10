package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.AST

interface Analyzer {
    fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult
}
