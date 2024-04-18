package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken

// Here should go only expressions with println.

/*Inside of parenthesis should go only expressions, so i call the Expression Parser.
* println(1+1) is valid
* println(a + b) where a and b are variables is valid
 */
class PrintlnParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val indexCopy = input.index
        val currentToken =
            currentToken(input.tokens, indexCopy) ?: throw Exception(NoTokenFoundErrorMessage(indexCopy).toString())
        return when (currentToken.tokenType) {
            PrintlnKeyword -> parsePrintLn(input.tokens, indexCopy)
            else -> throw Exception(ExpectedTokenErrorMessage("println", currentToken).toString())
        }
    }

    private fun parsePrintLn(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        val consumeResult = consumeToken(tokens, index)
        val token = consumeResult.first ?: throw Exception(NoTokenFoundErrorMessage(consumeResult.second).toString())
        val newIndex = consumeResult.second
        val currentToken =
            currentToken(tokens, newIndex) ?: throw Exception(NoTokenFoundErrorMessage(newIndex).toString())
        if (currentToken.tokenType != LeftParenthesis) {
            throw Exception(ExpectedTokenErrorMessage("(", currentToken).toString())
        }
        val childNode = ExpressionParser().parse(InputContext(tokens, index + 1))

        val node = PrintLnNode(token.tokenPosition, childNode.first as Expression)
        val forwardIndex = if (childNode.second == tokens.size - 1) (childNode.second) else (childNode.second + 1)
        return Pair(node, forwardIndex)
    }
}
