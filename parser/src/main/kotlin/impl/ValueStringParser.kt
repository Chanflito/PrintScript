package impl

import InputContext
import Parser
import ast.ASTNodeImpl
import ast.StringNode
import common.ast.ASTNode
import common.token.ValueString
import util.*

class ValueStringParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentToken= currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())
        if (currentToken.tokenType!= ValueString){
            throw Exception(ExpectedTokenErrorMessage("string",currentToken).toString())
        }
        val consumeResult = consumeToken(input.tokens,input.index)
        val token = consumeResult.first
        return Pair(ASTNodeImpl(token?.value, token, StringNode, null),consumeResult.second)
    }
}