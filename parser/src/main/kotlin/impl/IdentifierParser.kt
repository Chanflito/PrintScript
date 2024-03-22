package impl

import InputContext
import Parser
import ast.ASTNodeImpl
import ast.IdentifierNode
import common.ast.ASTNode
import common.token.Identifier
import util.*

class IdentifierParser  : Parser<InputContext>{
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val currentToken= currentToken(input.tokens, input.index) ?: throw Exception(NoTokenFoundErrorMessage(input.index).toString())
        if (currentToken.tokenType!= Identifier){
            throw Exception(ExpectedTokenErrorMessage("Identifier",currentToken).toString())
        }
        val consumeResult= consumeToken(input.tokens,input.index)
        val token = consumeResult.first
        return Pair(ASTNodeImpl(token?.value, token, IdentifierNode, null),consumeResult.second)
    }
}