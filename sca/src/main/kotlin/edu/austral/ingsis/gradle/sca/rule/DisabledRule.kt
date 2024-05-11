package edu.austral.ingsis.gradle.sca.rule

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.ReportResult
import edu.austral.ingsis.gradle.sca.ReportSuccess
import edu.austral.ingsis.gradle.sca.Rule

class DisabledRule : Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return ReportSuccess
    }
}
