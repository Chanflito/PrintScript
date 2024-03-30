package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
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
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val indexCopy = input.index
        val currentToken = currentToken(input.tokens, indexCopy) ?: throw Exception(NoTokenFoundErrorMessage(indexCopy).toString())
        return when (currentToken.tokenType) {
            PrintlnKeyword -> parsePrintLn(input.tokens, indexCopy)
            else -> throw Exception(ExpectedTokenErrorMessage("println", currentToken).toString())
        }
    }

    private fun parsePrintLn(
        tokens: List<Token>,
        index: Int,
    ): Pair<ASTNode, Int> {
        val consumeResult = consumeToken(tokens, index)
        val token = consumeResult.first
        val newIndex = consumeResult.second
        val currentToken = currentToken(tokens, newIndex) ?: throw Exception(NoTokenFoundErrorMessage(newIndex).toString())
        if (currentToken.tokenType != LeftParenthesis) {
            throw Exception(ExpectedTokenErrorMessage("(", currentToken).toString())
        }
        val childNode = ExpressionParser().parse(InputContext(tokens, index + 1))
        if (childNode.second == tokens.size - 1) {
            return Pair(ASTNodeImpl(token?.value, token, PrintLnNode, listOf(childNode.first)), childNode.second)
        }
        return Pair(ASTNodeImpl(token?.value, token, PrintLnNode, listOf(childNode.first)), childNode.second - 1)
    }
}
