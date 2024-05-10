package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignationNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class DeclarationAssignationNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is DeclarationAssignationNode) return ReportSuccess
        return analyzeDeclarationAssignation(ast, rules)
    }

    private fun analyzeDeclarationAssignation(
        declarationAssignation: DeclarationAssignationNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(declarationAssignation) }
        return generateReport(report)
    }
}
