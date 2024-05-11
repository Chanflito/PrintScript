package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface Statement : AST

data class BlockNode(
    override val tokenPosition: TokenPosition,
    val children: List<AST>,
) : Statement
