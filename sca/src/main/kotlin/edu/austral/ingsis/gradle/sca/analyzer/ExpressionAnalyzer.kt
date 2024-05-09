package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class ExpressionAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is Expression) return ReportSuccess
        return analyzeExpression(ast, rules)
    }

    private fun analyzeExpression(
        expression: Expression,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(expression) }
        return generateReport(report)
    }
}
