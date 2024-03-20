package impl

import common.ast.ASTNode
import common.token.Token
import Parser
import ast.ASTNodeImpl
import ast.AssignationNode
import ast.IdentifierNode
import common.token.Assignation
import common.token.Identifier
import util.consumeToken

//Here should go assignations like a=7
class AssignationParser (private val startIndex: Int) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        val index = startIndex
        val identifierToken = consumeToken(tokens, index)
        require(getToken(identifierToken)?.tokenType == Identifier) { "Expected identifier" }
        val assignationToken = consumeToken(tokens, getIndex(identifierToken))
        require(getToken(assignationToken)?.tokenType == Assignation) { "Expected assignation" }
        return ASTNodeImpl(
            "=",
            getToken(assignationToken),
            AssignationNode,
            listOf(
                ASTNodeImpl(getToken(identifierToken)?.value, getToken(identifierToken), IdentifierNode, null),
                ExpressionParser(getIndex(assignationToken)).parse(tokens)
            )
        )
    }

    private fun getIndex(assignationToken: Pair<Token?, Int>) = assignationToken.second

    private fun getToken(identifierToken: Pair<Token?, Int>) = identifierToken.first

}
