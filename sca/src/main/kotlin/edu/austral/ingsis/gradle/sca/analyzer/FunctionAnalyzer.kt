package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.factory.createComposeAnalyzer
import edu.austral.ingsis.gradle.sca.util.generateReport

class PrintLnNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is PrintLnNode) return ReportSuccess
        return analyzePrintLnNode(ast, rules)
    }

    private fun analyzePrintLnNode(
        function: PrintLnNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(function) }
        val result = createComposeAnalyzer().analyze(function.expression, rules)
        return generateReport(report + result)
    }
}

class ReadInputNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is ReadInputNode) return ReportSuccess
        return analyzeReadInputNode(ast, rules)
    }

    private fun analyzeReadInputNode(
        function: ReadInputNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report = rules.map { it.verify(function) }
        val result = createComposeAnalyzer().analyze(function.expression, rules)
        return generateReport(report + result)
    }
}
