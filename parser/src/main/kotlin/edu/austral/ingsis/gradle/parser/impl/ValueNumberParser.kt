package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.NumberLiteralNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isNumberValue

class ValueNumberParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (currentToken, next) = consumeToken(input.tokens, input.index)

        if (!isNumberValue(currentToken)) {
            throw MissingTokenException(currentToken, "Number")
        }

        val parsedValue = currentToken.value.toDouble()
        return Pair(NumberLiteralNode(parsedValue, currentToken.tokenPosition), next)
    }
}
