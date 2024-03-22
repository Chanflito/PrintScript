package impl

import InputContext
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
class AssignationParser  : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode,Int> {
        val index = input.index
        val identifierToken = consumeToken(input.tokens, index)
        require(getToken(identifierToken)?.tokenType == Identifier) { "Expected identifier" }
        val assignationToken = consumeToken(input.tokens, getIndex(identifierToken))
        require(getToken(assignationToken)?.tokenType == Assignation) { "Expected assignation" }
        return Pair(
            ASTNodeImpl(
                "=",
                getToken(assignationToken),
                AssignationNode,
                listOf(
                    ASTNodeImpl(getToken(identifierToken)?.value, getToken(identifierToken), IdentifierNode, null),
                    ExpressionParser().parse(InputContext(input.tokens, getIndex(assignationToken))).first
                ),
            ), getIndex(assignationToken)
        )
    }

    private fun getIndex(assignationToken: Pair<Token?, Int>) = assignationToken.second

    private fun getToken(identifierToken: Pair<Token?, Int>) = identifierToken.first

}
