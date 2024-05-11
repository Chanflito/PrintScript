package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.AST

interface Analyzer {
    /**
     * You should implement how to analyze the AST with the given rules
     * @param ast the AST to analyze
     * @param rules the rules to apply
     * @return the report result
     **/
    fun analyze(
        ast: AST,
        rules: List<Rule<AST>>,
    ): ReportResult
}
