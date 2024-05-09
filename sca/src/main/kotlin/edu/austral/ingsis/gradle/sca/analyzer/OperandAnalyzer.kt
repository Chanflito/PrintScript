package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.Operand
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class OperandAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is Operand) return ReportSuccess
        return analyzeOperand(ast, rules)
    }

    private fun analyzeOperand(
        operand: Operand,
        rules: List<Rule<AST>>,
    ): ReportResult {
        return when (operand) {
            is IdentifierNode -> {
                val result = rules.map { it.verify(operand) }
                return generateReport(result)
            }
            else -> ReportSuccess
        }
    }
}
