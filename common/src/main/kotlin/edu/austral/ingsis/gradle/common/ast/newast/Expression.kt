package edu.austral.ingsis.gradle.common.ast.newast

import edu.austral.ingsis.gradle.common.token.TokenPosition

sealed interface Expression : Statement

sealed interface Operand : Expression

interface Operator : Expression {
    val value: String
}

interface Literal : Operand {
    val type: Type
    val value: String
}

data class MultiplyNode(override val value: String = "*", override val tokenPosition: TokenPosition) : Operator

data class DivideNode(override val value: String = "/", override val tokenPosition: TokenPosition) : Operator

data class SumNode(override val value: String = "+", override val tokenPosition: TokenPosition) : Operator

data class SubtractNode(override val value: String = "-", override val tokenPosition: TokenPosition) : Operator

data class Identifier(val name: String, override val tokenPosition: TokenPosition) : Operand

data class StringLiteral(override val value: String, override val tokenPosition: TokenPosition) : Literal {
    override val type: Type = StringType
}

data class NumberLiteral(override val value: String, override val tokenPosition: TokenPosition) : Literal {
    override val type: Type = NumberType
}

data class BooleanLiteral(override val value: String, override val tokenPosition: TokenPosition) : Literal {
    override val type: Type = BooleanType
}
