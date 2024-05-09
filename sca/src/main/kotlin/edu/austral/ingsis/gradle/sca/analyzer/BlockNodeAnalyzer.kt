package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.common.ast.Function
import edu.austral.ingsis.gradle.common.ast.Statement
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class BlockNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is BlockNode) return ReportSuccess
        return analyzeChildren(ast.children, rules)
    }

    private fun analyzeChildren(
        children: List<AST>,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val reports =
            children.map {
                when (it) {
                    is Statement -> StatementAnalyzer().analyze(it, rules)
                    is Function -> FunctionAnalyzer().analyze(it, rules)
                    else -> ReportSuccess
                }
            }
        return generateReport(reports)
    }
}
