package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.ASTNode

class DisabledRule : Rule<ASTNode> {
    override fun verify(node: ASTNode): ReportResult {
        return ReportSuccess
    }
}
