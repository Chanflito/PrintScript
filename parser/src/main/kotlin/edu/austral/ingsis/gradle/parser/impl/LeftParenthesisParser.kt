package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.builder.EXPRESSION_PARSER_MAP
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis
import edu.austral.ingsis.gradle.parser.util.isRightParenthesis

class LeftParenthesisParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (currentToken, expressionIndex) = consumeToken(input.tokens, input.index)

        if (!isLeftParenthesis(currentToken)) {
            throw MissingTokenException(currentToken, "(")
        }

        val innerExpression = ExpressionParser(EXPRESSION_PARSER_MAP).parse(InputContext(input.tokens, expressionIndex))

        val (rightParenthesisToken, next) = consumeToken(input.tokens, innerExpression.second)

        if (!isRightParenthesis(rightParenthesisToken)) {
            throw MissingTokenException(rightParenthesisToken, ")")
        }

        return Pair(innerExpression.first, next)
    }
}
