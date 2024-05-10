package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.ReAssignationNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class ReAssignationNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is ReAssignationNode) return ReportSuccess
        return analyzeReAssignation(ast, rules)
    }

    private fun analyzeReAssignation(
        reAssignation: ReAssignationNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(reAssignation) }
        return generateReport(report)
    }
}
