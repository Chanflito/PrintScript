package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

sealed interface Literal<T> : Operand {
    val nodeType: NodeType
    val value: T
}

data class NumberLiteralNode(
    override val value: Number,
    override val tokenPosition: TokenPosition,
) : Literal<Number> {
    override val nodeType: NodeType = NumberNodeType
}

data class StringLiteralNode(
    override val value: String,
    override val tokenPosition: TokenPosition,
) : Literal<String> {
    override val nodeType: NodeType = StringNodeType
}

data class BooleanLiteralNode(
    override val value: Boolean,
    override val tokenPosition: TokenPosition,
) : Literal<Boolean> {
    override val nodeType: NodeType = BooleanNodeType
}
