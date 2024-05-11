package edu.austral.ingsis.gradle.sca.analyzer

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.Analyzer
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.factory.createComposeAnalyzer

class DefaultAnalyzer : Analyzer {
    override fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult {
        return createComposeAnalyzer().analyze(ast, rules)
    }
}
