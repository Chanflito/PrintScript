package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.sca.util.generateReport

class ComposeRule(private val rules: List<Rule<AST>>) : Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return generateReport(rules.map { it.verify(node) })
    }
}
