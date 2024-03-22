package impl

import InputContext
import Parser
import common.ast.ASTNode
import common.token.LeftParenthesis
import util.EXPRESSION_PARSER_MAP
import util.consumeToken
import util.currentToken
import util.isRightParenthesis

class LeftParenthesisParser : Parser<InputContext>{
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentType= currentToken(input.tokens, input.index)?.tokenType
        if (currentType!=LeftParenthesis){
            throw Exception("Unexpected token: $currentType")
        }
        val consumeResult= consumeToken(input.tokens,input.index)
        val innerExpression= ExpressionParser(EXPRESSION_PARSER_MAP).parse(InputContext(input.tokens, consumeResult.second))
        val nextToken= consumeToken(input.tokens,innerExpression.second)
        require(isRightParenthesis(nextToken.first)){
            "Unexpected token: expected ) after expression"
        }
        return Pair(innerExpression.first,nextToken.second)
    }
}