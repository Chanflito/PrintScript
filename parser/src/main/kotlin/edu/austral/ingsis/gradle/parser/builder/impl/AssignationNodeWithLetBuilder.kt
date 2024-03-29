package edu.austral.ingsis.gradle.parser.builder.impl

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.builder.AstBuilder
import edu.austral.ingsis.gradle.parser.impl.ExpressionParser
import edu.austral.ingsis.gradle.parser.util.consumeToken

class AssignationNodeWithLetBuilder : AstBuilder<ASTNodeImpl> {
    override fun build(
        tokens: List<Token>,
        index: Int,
    ): Pair<ASTNodeImpl, Int> {
        val letNode = createLetNode(tokens, index)
        val identifierToken = getIdentifierToken(tokens, letNode.second)
        val typeTokenPair = getTypeToken(tokens, identifierToken.second + 1)
        val equalsToken = getEqualsToken(tokens, typeTokenPair.second)
        return Pair(
            ASTNodeImpl(
                equalsToken.first?.value,
                equalsToken.first,
                AssignationNode,
                listOf(
                    IdentifierNodeWithLetBuilder().build(tokens, index).first,
                    ExpressionParser().parse(InputContext(tokens, equalsToken.second)).first,
                ),
            ),
            equalsToken.second,
        )
    }

    private fun createLetNode(
        tokens: List<Token>,
        index: Int,
    ): Pair<ASTNodeImpl, Int> {
        val consumeResult = consumeToken(tokens, index)
        val letNode = ASTNodeImpl("let", consumeResult.first, KeywordNode, listOf())
        return Pair(letNode, consumeResult.second)
    }

    private fun getIdentifierToken(
        tokens: List<Token>,
        index: Int,
    ): Pair<Token?, Int> {
        val currentToken = consumeToken(tokens, index)
        return Pair(currentToken.first, currentToken.second)
    }

    private fun getTypeToken(
        tokens: List<Token>,
        index: Int,
    ): Pair<Token?, Int> {
        val currentToken = consumeToken(tokens, index)
        return Pair(currentToken.first, currentToken.second)
    }

    private fun getEqualsToken(
        tokens: List<Token>,
        index: Int,
    ): Pair<Token?, Int> {
        val currentToken = consumeToken(tokens, index)
        return Pair(currentToken.first, currentToken.second)
    }
}
