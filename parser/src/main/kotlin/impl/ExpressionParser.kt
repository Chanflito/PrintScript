package impl

import common.ast.ASTNode
import common.token.Token
import Parser
//Here should go expressions like 5+5, 7+2
class ExpressionParser(private val index: Int) : Parser {

    override fun parse(tokens: List<Token>): ASTNode {
        TODO("Not yet implemented")
    }
}