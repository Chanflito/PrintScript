package impl

import InputContext
import Parser
import ast.ASTNodeImpl
import ast.StringNode
import common.ast.ASTNode
import common.token.ValueString
import util.consumeToken
import util.currentToken

class ValueStringParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentType= currentToken(input.tokens, input.index)?.tokenType
        if (currentType!= ValueString){
            throw Exception("Unexpected token: $currentType")
        }
        val consumeResult = consumeToken(input.tokens,input.index)
        val token = consumeResult.first
        return Pair(ASTNodeImpl(token?.value, token, StringNode, null),consumeResult.second)
    }
}