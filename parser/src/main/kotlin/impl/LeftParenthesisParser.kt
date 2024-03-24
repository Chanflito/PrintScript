package impl

import InputContext
import Parser
import ast.ASTNode
import common.token.LeftParenthesis
import common.token.Token
import util.*

class LeftParenthesisParser : Parser<InputContext>{
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentToken= currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())
        if (currentToken.tokenType!=LeftParenthesis){
            throw Exception(ExpectedTokenErrorMessage("(",currentToken).toString())
        }
        val consumeResult= consumeToken(input.tokens,input.index)
        val innerExpression= ExpressionParser(EXPRESSION_PARSER_MAP).parse(InputContext(input.tokens, consumeResult.second))
        val consumeTokenResult= consumeToken(input.tokens,innerExpression.second)
        val token= consumeTokenResult.first
        val index= consumeTokenResult.second
        verifyIfRightParenthesisExists(token, innerExpression)
        return Pair(innerExpression.first,index)
    }

    private fun verifyIfRightParenthesisExists(token: Token?, innerExpression: Pair<ASTNode, Int>) {
        require(isRightParenthesis(token)) {
            when {
                token == null -> throw Exception(NoTokenFoundErrorMessage(innerExpression.second).toString())
                else -> throw Exception(ExpectedTokenErrorMessage(")", token).toString())
            }
        }
    }
}