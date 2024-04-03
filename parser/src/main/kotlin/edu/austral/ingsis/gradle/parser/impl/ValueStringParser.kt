package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.token.ValueString
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken

class ValueStringParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentToken = currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())
        if (currentToken.tokenType != ValueString) {
            throw Exception(ExpectedTokenErrorMessage("string", currentToken).toString())
        }
        val consumeResult = consumeToken(input.tokens, input.index)
        val token = consumeResult.first
        return Pair(ASTNodeImpl(token?.value, token, StringNode, listOf()), consumeResult.second)
    }
}
