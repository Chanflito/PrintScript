package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isIdentifier

class IdentifierParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (currentToken, next) = consumeToken(input.tokens, input.index)

        if (!isIdentifier(currentToken)) {
            throw MissingTokenException(currentToken, "identifier")
        }

        return Pair(IdentifierNode(currentToken.value, currentToken.tokenPosition), next)
    }
}
