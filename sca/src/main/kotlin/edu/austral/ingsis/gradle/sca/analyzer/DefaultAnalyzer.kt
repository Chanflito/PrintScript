package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Function
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.Statement
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule

class DefaultAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        return when (ast) {
            is ProgramNode -> ProgramNodeAnalyzer().analyze(ast, rules)
            is Statement -> StatementAnalyzer().analyze(ast, rules)
            is Function -> FunctionAnalyzer().analyze(ast, rules)
            else -> ReportSuccess
        }
    }
}
