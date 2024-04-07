package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

data class ProgramNode(override val tokenPosition: TokenPosition, val children: List<Statement>) : AST {
    fun getChildren(): List<Statement> {
        return children
    }
}
