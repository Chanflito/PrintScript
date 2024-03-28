package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.sca.util.generateReport

class ComposeRule(private val rules: List<Rule<ASTNode>>) : Rule<ASTNode> {
    override fun verify(node: ASTNode): ReportResult {
        return generateReport(rules.map { it.verify(node) })
    }
}
