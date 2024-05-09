package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis
import edu.austral.ingsis.gradle.parser.util.isRightParenthesis

class ReadEnvParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (readEnvToken, parenthesisIndex) = consumeToken(input.tokens, input.index)

        val (leftParenthesisToken, expressionIndex) = consumeToken(input.tokens, parenthesisIndex)

        if (!isLeftParenthesis(leftParenthesisToken)) {
            throw MissingTokenException(leftParenthesisToken, "(")
        }

        val (variableName, rightParenthesisIndex) = consumeToken(input.tokens, expressionIndex)

        val (rightParenthesisToken, next) = consumeToken(input.tokens, rightParenthesisIndex)

        if (!isRightParenthesis(rightParenthesisToken)) {
            throw MissingTokenException(rightParenthesisToken, ")")
        }

        return Pair(
            ReadEnvNode(
                readEnvToken.tokenPosition,
                variableName.value,
            ),
            next,
        )
    }
}
