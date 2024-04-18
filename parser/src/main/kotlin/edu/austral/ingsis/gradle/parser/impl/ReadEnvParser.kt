package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis

class ReadEnvParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (readEnvToken, parenthesisIndex) = consumeToken(input.tokens, input.index)
        val readEnv = readEnvToken ?: throw Exception(NoTokenFoundErrorMessage(parenthesisIndex).toString())
        val (leftParenthesisToken, expressionIndex) = consumeToken(input.tokens, parenthesisIndex)

        if (!isLeftParenthesis(leftParenthesisToken)) {
            throw Exception(ExpectedTokenErrorMessage("(", leftParenthesisToken!!).toString())
        }

        val (value, rightParenthesisIndex) = consumeToken(input.tokens, expressionIndex)
        val variableName = value ?: throw Exception(NoTokenFoundErrorMessage(rightParenthesisIndex).toString())

        return Pair(
            ReadEnvNode(
                readEnv.tokenPosition,
                variableName.value,
            ),
            rightParenthesisIndex,
        )
    }
}
