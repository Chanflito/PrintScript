package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.InvalidTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.endOfFile
import edu.austral.ingsis.gradle.parser.util.isSemiColon

class ComposeParser(
    private val parsers: Map<TokenType, Parser<InputContext>>,
) :
    Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val indexCopy = input.index + 1
        if (input.tokens.size == 1 && !endOfFile(input.tokens, input.index)) return handleResult(input)
        if (endOfFile(input.tokens, indexCopy) || currentToken(input.tokens, indexCopy) == null) {
            throw Exception("NULL TOKEN")
        }
        return if (isSemiColon(input.tokens[indexCopy])) {
            this.parse(InputContext(input.tokens, indexCopy + 1))
        } else {
            handleResult(input)
        }
    }

    private fun handleResult(input: InputContext): Pair<AST, Int> {
        val result = currentToken(input.tokens, input.index)
        val parserFound =
            parsers[result?.tokenType] ?: throw Exception(result?.let { InvalidTokenErrorMessage(it).toString() })
        return parseWith(input, parserFound)
    }

    private fun parseWith(
        input: InputContext,
        parser: Parser<InputContext>,
    ): Pair<AST, Int> {
        return parser.parse(InputContext(input.tokens, input.index))
    }
}
