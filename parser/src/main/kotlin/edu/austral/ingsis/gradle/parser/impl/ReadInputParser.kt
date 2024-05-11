package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Expression
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis
import edu.austral.ingsis.gradle.parser.util.isRightParenthesis

class ReadInputParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (readInputToken, parenthesisIndex) = consumeToken(input.tokens, input.index)

        val (parenthesis, expressionIndex) = consumeToken(input.tokens, parenthesisIndex)

        if (!isLeftParenthesis(parenthesis)) {
            throw MissingTokenException(parenthesis, "(")
        }
        val (expression, rightParenthesisIndex) = ExpressionParser().parse(InputContext(input.tokens, expressionIndex))

        val (rightParenthesis, next) = consumeToken(input.tokens, rightParenthesisIndex)

        if (!isRightParenthesis(rightParenthesis)) {
            throw MissingTokenException(rightParenthesis, ")")
        }

        return Pair(
            ReadInputNode(
                readInputToken.tokenPosition,
                expression as Expression,
            ),
            next,
        )
    }
}
