package edu.austral.ingsis.gradle.parser.type

import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.token.Token

val TOKEN_TYPES_MAP =
    mapOf(
        "number" to NumberTokenBuilder(),
        "string" to StringTokenBuilder(),
        "boolean" to BooleanTokenBuilder(),
    )

interface TokenTypeBuilder {
    fun execute(
        token: Token,
        index: Int,
    ): Pair<NodeType, Int>
}

class NumberTokenBuilder : TokenTypeBuilder {
    override fun execute(
        token: Token,
        index: Int,
    ): Pair<NodeType, Int> {
        return Pair(NumberNodeType, index)
    }
}

class StringTokenBuilder : TokenTypeBuilder {
    override fun execute(
        token: Token,
        index: Int,
    ): Pair<NodeType, Int> {
        return Pair(StringNodeType, index)
    }
}

class BooleanTokenBuilder : TokenTypeBuilder {
    override fun execute(
        token: Token,
        index: Int,
    ): Pair<NodeType, Int> {
        return Pair(BooleanNodeType, index)
    }
}
