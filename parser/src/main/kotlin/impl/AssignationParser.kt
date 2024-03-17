package impl

import common.ast.ASTNode
import common.token.Token
import Parser
//Here should go assignations like a=7
class AssignationParser (private val index: Int) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        TODO("Not yet implemented")
    }
}