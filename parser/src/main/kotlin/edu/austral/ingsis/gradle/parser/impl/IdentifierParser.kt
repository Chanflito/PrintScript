package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken

class IdentifierParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val currentToken =
            currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())
        if (currentToken.tokenType != Identifier) {
            throw Exception(ExpectedTokenErrorMessage("Identifier", currentToken).toString())
        }
        val consumeResult = consumeToken(input.tokens, input.index)
        val token = consumeResult.first ?: throw Exception(NoTokenFoundErrorMessage(consumeResult.second).toString())
        return Pair(IdentifierNode(token.value, token.tokenPosition), consumeResult.second)
    }
}
