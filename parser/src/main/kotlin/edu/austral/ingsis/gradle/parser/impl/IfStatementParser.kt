package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken

class IfStatementParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<IfStatement, Int> {
        val (ifToken, ifIndex) = consumeToken(input.tokens, input.index)

        val ifStart = ifToken ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())

        val (leftParenthesisToken, leftParenthesisIndex) = consumeToken(input.tokens, ifIndex)

        val (condition, conditionIndex) = ExpressionParser().parse(InputContext(input.tokens, leftParenthesisIndex))

        val (rightParenthesisToken, rightParenthesisIndex) = consumeToken(input.tokens, conditionIndex)

        val (leftBraceToken, leftBraceIndex) = consumeToken(input.tokens, rightParenthesisIndex)

        val (ifBlock, endifBlockIndex) = BlockParser().parse(InputContext(input.tokens, leftBraceIndex))

        return Pair(
            IfStatement(
                ifStart.tokenPosition,
                condition as Expression,
                ifBlock,
            ),
            endifBlockIndex,
        )
    }
}
