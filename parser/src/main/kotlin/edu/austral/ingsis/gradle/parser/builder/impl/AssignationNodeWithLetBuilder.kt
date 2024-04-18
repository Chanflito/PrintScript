package edu.austral.ingsis.gradle.parser.builder.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.builder.AstBuilder
import edu.austral.ingsis.gradle.parser.impl.ExpressionParser
import edu.austral.ingsis.gradle.parser.util.consumeToken

class AssignationNodeWithLetBuilder : AstBuilder<AST> {
    override fun build(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        val letNode = createLetNode(tokens, index)
        val identifierToken = getIdentifierToken(tokens, letNode.second)
        val typeTokenPair = getTypeToken(tokens, identifierToken.second + 1)
        val equalsTokenPair = getEqualsToken(tokens, typeTokenPair.second)
        val expressionResult = ExpressionParser().parse(InputContext(tokens, equalsTokenPair.second))
        val equalsToken = equalsTokenPair.first ?: throw Exception("No token found")
        return Pair(
            DeclarationAssignation(
                letNode.first,
                equalsToken.tokenPosition,
                typeTokenPair.first,
                identifierToken.first,
                expressionResult.first as Expression,
            ),
            expressionResult.second + 1,
        )
    }

    private fun createLetNode(
        tokens: List<Token>,
        index: Int,
    ): Pair<LetKeywordNode, Int> {
        val consumeResult = consumeToken(tokens, index)
        val token = consumeResult.first ?: throw Exception("No token found")
        val letNode = LetKeywordNode(token.tokenPosition)
        return Pair(letNode, consumeResult.second)
    }

    private fun getIdentifierToken(
        tokens: List<Token>,
        index: Int,
    ): Pair<IdentifierNode, Int> {
        val currentToken = consumeToken(tokens, index)
        val token = currentToken.first ?: throw Exception("No token found")
        return Pair(IdentifierNode(token.value, token.tokenPosition), currentToken.second)
    }

    private fun getTypeToken(
        tokens: List<Token>,
        index: Int,
    ): Pair<NodeType, Int> {
        val currentToken = consumeToken(tokens, index)
        return when (currentToken.first?.value) {
            "number" -> Pair(NumberNodeType, currentToken.second)
            "string" -> Pair(StringNodeType, currentToken.second)
            "boolean" -> Pair(BooleanNodeType, currentToken.second)
            else -> throw Exception("Invalid type")
        }
    }

    private fun getEqualsToken(
        tokens: List<Token>,
        index: Int,
    ): Pair<Token?, Int> {
        val currentToken = consumeToken(tokens, index)
        return Pair(currentToken.first, currentToken.second)
    }
}
