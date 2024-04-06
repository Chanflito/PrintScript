package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface ControlStatement : Statement

data class IfStatement(
    override val tokenPosition: TokenPosition,
    val condition: Expression,
    val ifBlock: Block,
) : ControlStatement

data class IfElseStatement(
    override val tokenPosition: TokenPosition,
    val condition: Expression,
    val ifBlock: Block,
    val elseBlock: Block,
) : ControlStatement
