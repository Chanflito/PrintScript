package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DivideNode
import edu.austral.ingsis.gradle.common.ast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.factory.createComposeAnalyzer
import edu.austral.ingsis.gradle.sca.util.generateReport

class SumNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is SumNode) return ReportSuccess
        return analyzeSumNode(ast, rules)
    }

    private fun analyzeSumNode(
        sum: SumNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val left = sum.left
        val right = sum.right
        val leftResult = createComposeAnalyzer().analyze(left, rules)
        val rightResult = createComposeAnalyzer().analyze(right, rules)
        return generateReport(listOf(leftResult, rightResult))
    }
}

class SubtractNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is SubtractNode) return ReportSuccess
        return analyzeSubtractNode(ast, rules)
    }

    private fun analyzeSubtractNode(
        sub: SubtractNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val left = sub.left
        val right = sub.right
        val leftResult = createComposeAnalyzer().analyze(left, rules)
        val rightResult = createComposeAnalyzer().analyze(right, rules)
        return generateReport(listOf(leftResult, rightResult))
    }
}

class MultiplyNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is MultiplyNode) return ReportSuccess
        return analyzeMultiplyNode(ast, rules)
    }

    private fun analyzeMultiplyNode(
        multiply: MultiplyNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val left = multiply.left
        val right = multiply.right
        val leftResult = createComposeAnalyzer().analyze(left, rules)
        val rightResult = createComposeAnalyzer().analyze(right, rules)
        return generateReport(listOf(leftResult, rightResult))
    }
}

class DivideNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is DivideNode) return ReportSuccess
        return analyzeDivideNode(ast, rules)
    }

    private fun analyzeDivideNode(
        divide: DivideNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val left = divide.left
        val right = divide.right
        val leftResult = createComposeAnalyzer().analyze(left, rules)
        val rightResult = createComposeAnalyzer().analyze(right, rules)
        return generateReport(listOf(leftResult, rightResult))
    }
}
