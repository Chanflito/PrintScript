package edu.austral.ingsis.gradle.common.ast.newast

interface Declaration : Statement {
    val keyword: Keyword
    val identifier: Identifier
    val type: Type
}
