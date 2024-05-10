package edu.austral.ingsis.gradle.common.ast

import edu.austral.ingsis.gradle.common.token.TokenPosition

interface Operator : Expression {
    val operatorNodeType: OperatorNodeType
    val left: Expression
    val right: Expression
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
