package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Assignation
import edu.austral.ingsis.gradle.common.ast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.Declaration
import edu.austral.ingsis.gradle.common.ast.Expression
import edu.austral.ingsis.gradle.common.ast.Statement
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule

class StatementAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        if (ast !is Statement) return ReportSuccess
        return analyzeStatement(ast, rules)
    }

    private fun analyzeStatement(
        statement: Statement,
        rules: List<Rule<AST>>,
    ): ReportResult {
        return when (statement) {
            is Declaration -> DeclarationAnalyzer().analyze(statement, rules)
            is Assignation -> AssignationAnalyzer().analyze(statement, rules)
            is Expression -> ExpressionAnalyzer().analyze(statement, rules)
            is ControlStatement -> ControlStatementAnalyzer().analyze(statement, rules)
            else -> ReportSuccess
        }
    }
}
