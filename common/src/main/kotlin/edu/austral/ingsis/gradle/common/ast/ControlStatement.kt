package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface ControlStatement : Statement

data class IfStatementNode(
    override val tokenPosition: TokenPosition,
    val condition: Expression,
    val ifBlock: BlockNode,
) : ControlStatement

data class IfElseStatementNode(
    override val tokenPosition: TokenPosition,
    val condition: Expression,
    val ifBlock: BlockNode,
    val elseBlock: BlockNode,
) : ControlStatement
