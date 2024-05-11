package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class ComposeAnalyzer(private val analyzers: List<Analyzer>) : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val reports = analyzers.map { it.analyze(ast, rules) }
        return generateReport(reports)
    }
}
