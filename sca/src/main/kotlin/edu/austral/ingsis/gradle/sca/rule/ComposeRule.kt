package edu.austral.ingsis.gradle.sca.rule

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.Rule
import edu.austral.ingsis.gradle.sca.util.generateReport

class ComposeRule(private val rules: List<Rule<AST>>) : Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return generateReport(rules.map { it.verify(node) })
    }

    fun getRules(): List<Rule<AST>> {
        return rules
    }
}
