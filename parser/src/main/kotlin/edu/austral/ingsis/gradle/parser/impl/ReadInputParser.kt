package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis

class ReadInputParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val consumedPair = consumeToken(input.tokens, input.index)
        val currentToken = consumedPair.first ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())
        val (parenthesis, nextIndex) = consumeToken(input.tokens, consumedPair.second)
        if (!isLeftParenthesis(parenthesis)) {
            throw Exception(ExpectedTokenErrorMessage("(", currentToken).toString())
        }
        val (expression, next) = ExpressionParser().parse(InputContext(input.tokens, nextIndex))
        return Pair(
            ReadInputNode(
                currentToken.tokenPosition,
                expression as Expression,
            ),
            next,
        )
    }
}