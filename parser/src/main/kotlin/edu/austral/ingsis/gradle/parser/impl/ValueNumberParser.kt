package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isNumberValue

class ValueNumberParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val currentToken =
            currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())

        if (!isNumberValue(currentToken)) {
            throw Exception(ExpectedTokenErrorMessage("number", currentToken).toString())
        }

        val consumeResult = consumeToken(input.tokens, input.index)
        val token = consumeResult.first ?: throw Exception(NoTokenFoundErrorMessage(consumeResult.second).toString())
        val parsedValue = token.value.toDouble()
        return Pair(NumberLiteralNode(parsedValue, token.tokenPosition), consumeResult.second)
    }
}
