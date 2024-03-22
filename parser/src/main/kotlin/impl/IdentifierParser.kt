package impl

import InputContext
import Parser
import ast.ASTNodeImpl
import ast.IdentifierNode
import common.ast.ASTNode
import common.token.Identifier
import util.consumeToken
import util.currentToken

class IdentifierParser  : Parser<InputContext>{
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentType= currentToken(input.tokens, input.index)?.tokenType
        if (currentType!= Identifier){
            throw Exception("Unexpected token: $currentType")
        }
        val consumeResult= consumeToken(input.tokens,input.index)
        val token = consumeResult.first
        return Pair(ASTNodeImpl(token?.value, token, IdentifierNode, null),consumeResult.second)
    }
}