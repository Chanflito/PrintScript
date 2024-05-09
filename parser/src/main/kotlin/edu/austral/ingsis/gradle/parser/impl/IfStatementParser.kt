package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.Expression
import edu.austral.ingsis.gradle.common.ast.IfStatement
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isLeftBrace
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis
import edu.austral.ingsis.gradle.parser.util.isRightParenthesis

class IfStatementParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<IfStatement, Int> {
        val (ifToken, parenthesisIndex) = consumeToken(input.tokens, input.index)

        val (leftParenthesisToken, conditionIndex) = consumeToken(input.tokens, parenthesisIndex)

        if (!isLeftParenthesis(leftParenthesisToken)) throw MissingTokenException(leftParenthesisToken, "(")

        val (condition, rightParIndex) = ExpressionParser().parse(InputContext(input.tokens, conditionIndex))

        val (rightParenthesisToken, braceIndex) = consumeToken(input.tokens, rightParIndex)

        if (!isRightParenthesis(rightParenthesisToken)) throw MissingTokenException(rightParenthesisToken, ")")

        val (leftBraceToken, blockIndex) = consumeToken(input.tokens, braceIndex)

        if (!isLeftBrace(leftBraceToken)) throw MissingTokenException(leftBraceToken, "{")

        val (ifBlock, endifBlockIndex) = BlockParser().parse(InputContext(input.tokens, blockIndex))

        return Pair(
            IfStatement(
                ifToken.tokenPosition,
                condition as Expression,
                ifBlock,
            ),
            endifBlockIndex,
        )
    }
}
