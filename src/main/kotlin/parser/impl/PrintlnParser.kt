package parser.impl

import common.ast.ASTNode
import common.token.Token
import parser.Parser
//Here should go only expressions with println.

class PrintlnParser (private val index: Int) : Parser{
    override fun parse(tokens: List<Token>): ASTNode {
        TODO("Not yet implemented")
    }
}