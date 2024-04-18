package edu.austral.ingsis.gradle.parser.builder.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.builder.AstBuilder
import edu.austral.ingsis.gradle.parser.util.consumeToken

class IdentifierNodeWithLetBuilder : AstBuilder<AST> {
    override fun build(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        val letNode = createLetNode(tokens, index)
        val identifierToken = getIdentifierToken(tokens, letNode.second)
        val colon = tokens[identifierToken.second]
        val typeToken = getTypeToken(tokens, identifierToken.second + 1)
        return Pair(
            DeclarationNode(
                letNode.first,
                colon.tokenPosition,
                typeToken.first,
                identifierToken.first,
            ),
            identifierToken.second + 1,
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
}
