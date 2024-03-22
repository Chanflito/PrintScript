package impl

import InputContext
import Parser
import ast.ASTNodeImpl
import ast.NumberNode
import common.ast.ASTNode
import common.token.ValueNumber
import util.*

class ValueNumberParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentToken= currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())
        if (currentToken.tokenType!=ValueNumber){
            throw Exception(ExpectedTokenErrorMessage("number",currentToken).toString())
        }
        val consumeResult = consumeToken(input.tokens, input.index)
        val token = consumeResult.first
        val parsedValue = token?.value?.toDoubleOrNull()
        return Pair(ASTNodeImpl(parsedValue,token, NumberNode,null),consumeResult.second)
    }
}