package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

sealed interface Expression : Statement

sealed interface Operand : Expression

sealed interface Operator : Expression {
    val operatorNodeType: OperatorNodeType
    val left: Expression
    val right: Expression
}

sealed interface Literal<T> : Operand {
    val nodeType: NodeType
    val value: T
}

data class SumNode(
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator {
    override val operatorNodeType: OperatorNodeType = SumOperatorNode

    override fun toString(): String = "+"
}

data class SubtractNode(
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator {
    override val operatorNodeType: OperatorNodeType = SubtractOperatorNode

    override fun toString(): String = "-"
}

data class MultiplyNode(
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator {
    override val operatorNodeType: OperatorNodeType = MultiplyOperatorNode

    override fun toString(): String = "*"
}

data class DivideNode(
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator {
    override val operatorNodeType: OperatorNodeType = DivideOperatorNode

    override fun toString(): String = "/"
}

data class IdentifierNode(
    val name: String,
    override val tokenPosition: TokenPosition,
) : Operand

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
