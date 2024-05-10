package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.IfElseStatementNode
import edu.austral.ingsis.gradle.common.ast.IfStatementNode
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
            is IfStatementNode -> {
                IfStatementAnalyzer().analyze(controlStatement, rules)
            }
            is IfElseStatementNode -> {
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
        if (ast !is IfStatementNode) return ReportSuccess
        return analyzeIfStatement(ast, rules)
    }

    private fun analyzeIfStatement(
        ifStatementNode: IfStatementNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val expressionResult = ExpressionAnalyzer().analyze(ifStatementNode.condition, rules)
        val blockNodeResult = BlockNodeAnalyzer().analyze(ifStatementNode.ifBlock, rules)
        val report = listOf(expressionResult, blockNodeResult)
        return generateReport(report)
    }
}

class IfElseStatementAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is IfElseStatementNode) return ReportSuccess
        return analyzeIfElseStatement(ast, rules)
    }

    private fun analyzeIfElseStatement(
        ifElseStatementNode: IfElseStatementNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val expressionResult = ExpressionAnalyzer().analyze(ifElseStatementNode.condition, rules)
        val ifBlockResult = BlockNodeAnalyzer().analyze(ifElseStatementNode.ifBlock, rules)
        val elseBlockResult = BlockNodeAnalyzer().analyze(ifElseStatementNode.elseBlock, rules)
        val report = listOf(expressionResult, ifBlockResult, elseBlockResult)
        return generateReport(report)
    }
}
