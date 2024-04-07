package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

data class BlockNode(override val tokenPosition: TokenPosition, val statements: List<Statement>) : Statement
