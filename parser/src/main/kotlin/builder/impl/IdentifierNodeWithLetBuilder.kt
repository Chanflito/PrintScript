package builder.impl

import ast.ASTNodeImpl
import ast.IdentifierNode
import ast.KeywordNode
import ast.TypeNode
import builder.AstBuilder
import common.token.Token
import util.consumeToken

class IdentifierNodeWithLetBuilder : AstBuilder<ASTNodeImpl> {
    override fun build(tokens: List<Token>, index: Int): Pair<ASTNodeImpl, Int> {
        val letNode = createLetNode(tokens, index);
        val identifierToken = getIdentifierToken(tokens, letNode.second)
        val typeToken = getTypeToken(tokens, identifierToken.second + 1)
        return Pair(
            ASTNodeImpl(
                identifierToken.first?.value,
                identifierToken.first,
                IdentifierNode,
                listOf(
                    letNode.first,
                    ASTNodeImpl(
                        typeToken.first?.value, typeToken.first, TypeNode, null
                    )
                )
            ), identifierToken.second + 1
        )
    }

    private fun createLetNode(tokens: List<Token>, index: Int): Pair<ASTNodeImpl, Int> {
        val consumeResult = consumeToken(tokens, index)
        val letNode = ASTNodeImpl("let", consumeResult.first, KeywordNode, null);
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
}