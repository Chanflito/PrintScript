package edu.austral.ingsis.gradle.sca.analyzer
import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.factory.createComposeAnalyzer
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
                createComposeAnalyzer().analyze(it, rules)
            }
        return generateReport(report)
    }
}
