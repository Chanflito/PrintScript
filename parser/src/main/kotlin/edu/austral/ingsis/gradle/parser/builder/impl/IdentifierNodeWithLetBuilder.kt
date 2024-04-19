package edu.austral.ingsis.gradle.parser.builder.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.ConstKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.Keyword
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
        val keywordNode = createKeywordNode(tokens, index)
        val identifierToken = getIdentifierToken(tokens, keywordNode.second)
        val colon = tokens[identifierToken.second]
        val typeToken = getTypeToken(tokens, identifierToken.second + 1)
        return Pair(
            DeclarationNode(
                keywordNode.first,
                colon.tokenPosition,
                typeToken.first,
                identifierToken.first,
            ),
            typeToken.second,
        )
    }

    private fun createKeywordNode(
        tokens: List<Token>,
        index: Int,
    ): Pair<Keyword, Int> {
        val consumeResult = consumeToken(tokens, index)
        val token = consumeResult.first ?: throw Exception("No token found")
        val letNode =
            when (token.value) {
                "let" -> LetKeywordNode(token.tokenPosition)
                "const" -> ConstKeywordNode(token.tokenPosition)
                else -> throw Exception("Invalid keyword")
            }
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
