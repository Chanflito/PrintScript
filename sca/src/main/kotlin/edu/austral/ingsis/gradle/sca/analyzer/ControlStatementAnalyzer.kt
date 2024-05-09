package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.IfStatement
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class ControlStatementAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is ControlStatement) return ReportSuccess
        return analyzeControlStatement(ast, rules)
    }

    private fun analyzeControlStatement(
        controlStatement: ControlStatement,
        rules: List<Rule<AST>>,
    ): ReportResult {
        return when (controlStatement) {
            is IfStatement -> {
                IfStatementAnalyzer().analyze(controlStatement, rules)
            }
            is IfElseStatement -> {
                IfElseStatementAnalyzer().analyze(controlStatement, rules)
            }
            else -> {
                val report = rules.map { it.verify(controlStatement) }
                generateReport(report)
            }
        }
    }
}

class IfStatementAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is IfStatement) return ReportSuccess
        return analyzeIfStatement(ast, rules)
    }

    private fun analyzeIfStatement(
        ifStatement: IfStatement,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val expressionResult = ExpressionAnalyzer().analyze(ifStatement.condition, rules)
        val blockNodeResult = BlockNodeAnalyzer().analyze(ifStatement.ifBlock, rules)
        val report = listOf(expressionResult, blockNodeResult)
        return generateReport(report)
    }
}

class IfElseStatementAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is IfElseStatement) return ReportSuccess
        return analyzeIfElseStatement(ast, rules)
    }

    private fun analyzeIfElseStatement(
        ifElseStatement: IfElseStatement,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val expressionResult = ExpressionAnalyzer().analyze(ifElseStatement.condition, rules)
        val ifBlockResult = BlockNodeAnalyzer().analyze(ifElseStatement.ifBlock, rules)
        val elseBlockResult = BlockNodeAnalyzer().analyze(ifElseStatement.elseBlock, rules)
        val report = listOf(expressionResult, ifBlockResult, elseBlockResult)
        return generateReport(report)
    }
}
