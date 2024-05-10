package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.Expression
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isLeftParenthesis
import edu.austral.ingsis.gradle.parser.util.isRightParenthesis

class PrintlnParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (tokens, index) = input
        // the first token will be the call to println, we consume the token and pass to the next
        val (printToken, parenthesisIndex) = consumeToken(tokens, index)
        // consume the (supposely) parenthesis
        val (leftParenthesisToken, expressionIndex) = consumeToken(tokens, parenthesisIndex)
        if (!isLeftParenthesis(leftParenthesisToken)) {
            throw ExpectedTokenException("(", leftParenthesisToken)
        }
        // consume and parse the expression inside the parenthesis
        val childNode = ExpressionParser().parse(InputContext(tokens, expressionIndex))
        // consume the right parenthesis
        val (rightParenthesisToken, next) = consumeToken(tokens, childNode.second)
        if (!isRightParenthesis(rightParenthesisToken)) {
            throw ExpectedTokenException(")", rightParenthesisToken)
        }
        // generate the println Node
        val node = PrintLnNode(printToken.tokenPosition, childNode.first as Expression)
        return Pair(node, next)
    }
}
