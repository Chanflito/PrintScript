package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class IdentifierNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is IdentifierNode) return ReportSuccess
        return analyzeOperator(ast, rules)
    }

    private fun analyzeOperator(
        operator: IdentifierNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val result = rules.map { it.verify(operator) }
        return generateReport(result)
    }
}
