package impl

import InputContext
import Parser
import ast.ASTNodeImpl
import ast.NumberNode
import common.ast.ASTNode
import common.token.ValueNumber
import util.consumeToken
import util.currentToken

class ValueNumberParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentType= currentToken(input.tokens, input.index)?.tokenType
        if (currentType!=ValueNumber){
            throw Exception("Unexpected token: $currentType")
        }
        val consumeResult = consumeToken(input.tokens, input.index)
        val token = consumeResult.first
        val parsedValue = token?.value?.toDoubleOrNull()
        return Pair(ASTNodeImpl(parsedValue,token, NumberNode,null),consumeResult.second)
    }
}