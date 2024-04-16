package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST

fun createDefaultFormatter(): Formatter<AST> {
    return ComposeFormatter(
        listOf(
            DeclarationAssignationFormatter(),
            ReAssignationFormatter(),
            IfStatementFormatter(),
            IfElseStatementFormatter(),
            PrintLnFormatter(),
            ReadInputFormatter(),
        ),
    )
}
