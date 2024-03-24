package edu.austral.ingsis.gradle.parser.builder.impl

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.builder.AstBuilder
import edu.austral.ingsis.gradle.parser.util.consumeToken


class IdentifierNodeWithLetBuilder : AstBuilder<ASTNodeImpl> {
    override fun build(tokens: List<Token>, index: Int): Pair<ASTNodeImpl, Int> {
        val letNode = createLetNode(tokens, index)
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
                        typeToken.first?.value, typeToken.first, TypeNode, listOf()
                    )
                )
            ), identifierToken.second + 1
        )
    }

    private fun createLetNode(tokens: List<Token>, index: Int): Pair<ASTNodeImpl, Int> {
        val consumeResult = consumeToken(tokens, index)
        val letNode = ASTNodeImpl("let", consumeResult.first, KeywordNode, listOf())
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