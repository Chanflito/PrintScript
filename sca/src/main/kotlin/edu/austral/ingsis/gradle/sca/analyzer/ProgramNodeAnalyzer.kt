package edu.austral.ingsis.gradle.sca.analyzer
import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Function
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.Statement
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class ProgramNodeAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is ProgramNode) return ReportSuccess
        return analyzeProgramNode(ast, rules)
    }

    private fun analyzeProgramNode(
        programNode: ProgramNode,
        rules: List<Rule<AST>>,
    ): ReportResult {
        val report =
            programNode.children.map {
                when (it) {
                    is Statement -> {
                        val result = StatementAnalyzer().analyze(it, rules)
                        result
                    }
                    is Function -> {
                        val result = FunctionAnalyzer().analyze(it, rules)
                        result
                    }
                    else -> ReportSuccess
                }
            }
        return generateReport(report)
    }
}
