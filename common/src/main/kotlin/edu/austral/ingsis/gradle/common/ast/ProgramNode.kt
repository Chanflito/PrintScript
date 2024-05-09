package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

data class ProgramNode(override val tokenPosition: TokenPosition, val children: List<AST>) : AST
