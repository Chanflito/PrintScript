package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.EXPRESSION_PARSER_MAP
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis
import edu.austral.ingsis.gradle.parser.util.isRightParenthesis

class LeftParenthesisParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val currentToken =
            currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())

        if (!isLeftParenthesis(currentToken)) {
            throw Exception(ExpectedTokenErrorMessage("(", currentToken).toString())
        }

        val consumeResult = consumeToken(input.tokens, input.index)
        val innerExpression =
            ExpressionParser(EXPRESSION_PARSER_MAP).parse(InputContext(input.tokens, consumeResult.second))
        val consumeTokenResult = consumeToken(input.tokens, innerExpression.second)
        val token = consumeTokenResult.first
        val index = consumeTokenResult.second
        verifyIfRightParenthesisExists(token, innerExpression)
        return Pair(innerExpression.first, index)
    }

    private fun verifyIfRightParenthesisExists(
        token: Token?,
        innerExpression: Pair<AST, Int>,
    ) {
        require(isRightParenthesis(token)) {
            when {
                token == null -> throw Exception(NoTokenFoundErrorMessage(innerExpression.second).toString())
                else -> throw Exception(ExpectedTokenErrorMessage(")", token).toString())
            }
        }
    }
}
