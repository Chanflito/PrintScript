package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.StringLiteral
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isStringValue

class ValueStringParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (currentToken, next) = consumeToken(input.tokens, input.index)

        if (!isStringValue(currentToken)) {
            throw MissingTokenException(currentToken, "String")
        }

        return Pair(StringLiteral(currentToken.value, currentToken.tokenPosition), next)
    }
}
