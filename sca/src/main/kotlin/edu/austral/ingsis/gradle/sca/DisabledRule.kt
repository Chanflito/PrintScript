package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.newast.AST

class DisabledRule : Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return ReportSuccess
    }
}
