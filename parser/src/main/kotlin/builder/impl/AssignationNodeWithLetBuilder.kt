package builder.impl

import InputContext
import ast.ASTNodeImpl
import ast.AssignationNode
import ast.KeywordNode
import builder.AstBuilder
import common.token.Token
import impl.ExpressionParser
import util.consumeToken

class AssignationNodeWithLetBuilder : AstBuilder<ASTNodeImpl> {
    override fun build(tokens: List<Token>, index: Int): Pair<ASTNodeImpl, Int> {
        val letNode = createLetNode(tokens, index)
        val identifierToken = getIdentifierToken(tokens, letNode.second)
        val typeTokenPair = getTypeToken(tokens, identifierToken.second + 1)
        val equalsToken=getEqualsToken(tokens,typeTokenPair.second)
        return Pair(
            ASTNodeImpl(
                equalsToken.first?.value,
                equalsToken.first,
                AssignationNode,
                listOf(
                    IdentifierNodeWithLetBuilder().build(tokens, index).first,
                    ExpressionParser().parse(InputContext(tokens, equalsToken.second)).first
                )
            ), equalsToken.second
        )
    }
    private fun createLetNode(tokens: List<Token>, index: Int): Pair<ASTNodeImpl, Int> {
        val consumeResult = consumeToken(tokens, index)
        val letNode = ASTNodeImpl("let", consumeResult.first, KeywordNode, null)
        return Pair(letNode, consumeResult.second)
    }

    private fun getIdentifierToken(tokens: List<Token>, index: Int): Pair<Token?, Int> {
        val currentToken = consumeToken(tokens, index)
        return Pair(currentToken.first, currentToken.second)
    }

    private fun getTypeToken(tokens: List<Token>, index: Int): Pair<Token?, Int> {
        val currentToken = consumeToken(tokens, index)
        return Pair(currentToken.first, currentToken.second)
    }

    private fun getEqualsToken(tokens: List<Token>, index: Int): Pair<Token?, Int> {
        val currentToken = consumeToken(tokens, index)
        return Pair(currentToken.first, currentToken.second)
    }
}