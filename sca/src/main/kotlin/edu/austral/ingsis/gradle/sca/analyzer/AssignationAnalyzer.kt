package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Assignation
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class AssignationAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is Assignation) return ReportSuccess
        return analyzeAssignation(ast, rules)
    }

    private fun analyzeAssignation(
        assignation: Assignation,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(assignation) }
        return generateReport(report)
    }
}
