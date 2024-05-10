package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.BooleanLiteralNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isBooleanValue

class BooleanValueParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val currentToken = currentToken(input.tokens, input.index)
        if (!isBooleanValue(currentToken)) {
            throw MissingTokenException(currentToken, "boolean")
        }
        val (token, next) = consumeToken(input.tokens, input.index)
        return when (token.value) {
            "true" -> Pair(BooleanLiteralNode(true, token.tokenPosition), next)
            "false" -> Pair(BooleanLiteralNode(false, token.tokenPosition), next)
            else -> throw Exception("Invalid boolean value")
        }
    }
}
