package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Operator
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class OperatorAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is Operator) return ReportSuccess
        return analyzeOperator(ast, rules)
    }

    private fun analyzeOperator(
        operator: Operator,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val left = operator.left
        val right = operator.right
        val leftReport = ExpressionAnalyzer().analyze(left, rules)
        val rightReport = ExpressionAnalyzer().analyze(right, rules)
        return generateReport(listOf(leftReport, rightReport))
    }
}
