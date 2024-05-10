package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignationNode
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.Expression
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.Keyword
import edu.austral.ingsis.gradle.common.ast.NodeType
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.keyword.KEYWORDS_MAP
import edu.austral.ingsis.gradle.parser.keyword.KeywordBuilder
import edu.austral.ingsis.gradle.parser.type.TOKEN_TYPES_MAP
import edu.austral.ingsis.gradle.parser.type.TokenTypeBuilder
import edu.austral.ingsis.gradle.parser.util.InvalidKeywordException
import edu.austral.ingsis.gradle.parser.util.InvalidTypeException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.endOfFile
import edu.austral.ingsis.gradle.parser.util.isAssignation

// Parser for assignations of type -> let a : number= 7; or let a : number;
class DeclarationParser(
    private val keywordMap: Map<String, KeywordBuilder> = KEYWORDS_MAP,
    private val tokenTypeMap: Map<String, TokenTypeBuilder> = TOKEN_TYPES_MAP,
) :
    Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (tokens, index) = input
        // create the keyword node
        val (keywordNode, identifierIndex) = createKeywordNode(tokens, index)
        // create the identifier node
        val (identifierNode, colonIndex) = getIdentifierNode(tokens, identifierIndex)
        // get the type of the variable. NOTE: colonIndex + 1 is because the colon is the next token after the identifier
        val (typeTokenNode, equalsIndex) = getTokenType(tokens, colonIndex + 1)
        // get the assignation token
        val (equalsToken, expressionIndex) = consumeToken(tokens, equalsIndex)

        // if the next token is not an assignation token, then it is a declaration
        if (endOfFile(tokens, expressionIndex) && !isAssignation(equalsToken)) {
            return createDeclarationNode(keywordNode, typeTokenNode, identifierNode, equalsIndex)
        }
        // if the next token is an assignation token, then it is a declaration assignation
        val (expression, next) = ExpressionParser().parse(InputContext(tokens, expressionIndex))
        return createDeclarationAssignationNode(
            keywordNode,
            equalsToken,
            typeTokenNode,
            identifierNode,
            expression,
            next,
        )
    }

    private fun createDeclarationNode(
        keywordNode: Keyword,
        typeTokenNode: NodeType,
        identifierNode: IdentifierNode,
        next: Int,
    ) = Pair(
        DeclarationNode(
            keywordNode,
            keywordNode.tokenPosition,
            typeTokenNode,
            identifierNode,
        ),
        next,
    )

    private fun createDeclarationAssignationNode(
        keywordNode: Keyword,
        equalsToken: Token,
        typeTokenNode: NodeType,
        identifierNode: IdentifierNode,
        expression: AST,
        next: Int,
    ) = Pair(
        DeclarationAssignationNode(
            keywordNode,
            equalsToken.tokenPosition,
            typeTokenNode,
            identifierNode,
            expression as Expression,
        ),
        next,
    )

    private fun createKeywordNode(
        tokens: List<Token>,
        index: Int,
    ): Pair<Keyword, Int> {
        val (token, next) = consumeToken(tokens, index)
        val keywordBuilder = keywordMap[token.value] ?: throw InvalidKeywordException(token)
        return keywordBuilder.execute(token, next)
    }

    private fun getIdentifierNode(
        tokens: List<Token>,
        index: Int,
    ): Pair<IdentifierNode, Int> {
        val (token, next) = consumeToken(tokens, index)
        return Pair(IdentifierNode(token.value, token.tokenPosition), next)
    }

    private fun getTokenType(
        tokens: List<Token>,
        index: Int,
    ): Pair<NodeType, Int> {
        val (token, next) = consumeToken(tokens, index)
        val tokenTypeBuilder = tokenTypeMap[token.value] ?: throw InvalidTypeException(token)
        return tokenTypeBuilder.execute(token, next)
    }
}
