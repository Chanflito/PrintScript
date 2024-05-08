package edu.austral.ingsis.gradle.parser.operation

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.Minus
import edu.austral.ingsis.gradle.common.token.Multiply
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.CustomException

val PRIMARY_OPERATIONS_MAP =
    mapOf(
        Plus to SumOperation(),
        Minus to SubtractOperation(),
    )

val SECONDARY_OPERATIONS_MAP =
    mapOf(
        Multiply to MultiplyOperation(),
        Divide to DivideOperation(),
    )

fun childrenAreString(
    left: AST,
    right: AST,
) = left is StringLiteral || right is StringLiteral

interface Operation {
    fun execute(
        left: AST,
        right: AST,
        operator: Token,
    ): AST
}

class SumOperation : Operation {
    override fun execute(
        left: AST,
        right: AST,
        operator: Token,
    ): AST {
        return SumNode(
            operator.tokenPosition,
            left as Expression,
            right as Expression,
        )
    }
}

class SubtractOperation : Operation {
    override fun execute(
        left: AST,
        right: AST,
        operator: Token,
    ): AST {
        if (childrenAreString(left, right)) throw CustomException("Cannot subtract strings")
        return SubtractNode(
            operator.tokenPosition,
            left as Expression,
            right as Expression,
        )
    }
}

class MultiplyOperation : Operation {
    override fun execute(
        left: AST,
        right: AST,
        operator: Token,
    ): AST {
        if (childrenAreString(left, right)) throw CustomException("Cannot multiply strings")
        return MultiplyNode(
            operator.tokenPosition,
            left as Expression,
            right as Expression,
        )
    }
}

class DivideOperation : Operation {
    override fun execute(
        left: AST,
        right: AST,
        operator: Token,
    ): AST {
        if (childrenAreString(left, right)) throw CustomException("Cannot divide strings")
        return DivideNode(
            operator.tokenPosition,
            left as Expression,
            right as Expression,
        )
    }
}
