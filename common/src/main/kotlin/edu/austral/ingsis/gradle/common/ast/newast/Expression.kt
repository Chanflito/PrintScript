package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

sealed interface Expression : Statement

sealed interface Operand : Expression

interface Operator : Expression {
    val value: String
    val left: Expression
    val right: Expression
}

sealed interface Literal<T> : Operand {
    val type: Type
    val value: T
}

data class MultiplyNode(
    override val value: String = "*",
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator

data class DivideNode(
    override val value: String = "/",
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator

data class SumNode(
    override val value: String = "+",
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator

data class SubtractNode(
    override val value: String = "-",
    override val tokenPosition: TokenPosition,
    override val left: Expression,
    override val right: Expression,
) : Operator

data class IdentifierNode(val name: String, override val tokenPosition: TokenPosition) : Operand

data class StringLiteralNode(
    override val value: String,
    override val type: Type,
    override val tokenPosition: TokenPosition,
) : Literal<String>

data class IntLiteralNode(override val value: Int, override val type: Type, override val tokenPosition: TokenPosition) :
    Literal<Int> // Should be a declared as number type.

data class DoubleLiteralNode(
    override val value: Double,
    override val type: Type,
    override val tokenPosition: TokenPosition,
) : Literal<Double> // // Should be a declared as number type.

data class BooleanLiteralNode(
    override val value: Boolean,
    override val type: Type,
    override val tokenPosition: TokenPosition,
) : Literal<Boolean>
