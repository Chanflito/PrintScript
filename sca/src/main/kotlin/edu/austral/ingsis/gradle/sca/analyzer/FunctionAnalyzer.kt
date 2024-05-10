package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Function
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class FunctionAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is Function) return ReportSuccess
        return analyzeFunction(ast, rules)
    }

    private fun analyzeFunction(
        function: Function,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(function) }
        return when (function) {
            is PrintLnNode -> {
                val expressionReport = ExpressionAnalyzer().analyze(function.expression, rules)
                generateReport(report + expressionReport)
            }
            is ReadInputNode -> {
                val expressionReport = ExpressionAnalyzer().analyze(function.expression, rules)
                generateReport(report + expressionReport)
            }
            else -> generateReport(report)
        }
    }
}
