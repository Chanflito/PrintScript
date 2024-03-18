package impl

import Parser
import ast.ASTNodeImpl
import common.ast.*
import common.token.*
import util.*

//Here should go assignations like let a : number= 7; only with let keyword
class DeclarationParser (private val index: Int) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        val astNode= ASTNodeImpl("Program",null, ProgramNode, listOf())
        val copyIndex = index
        return when (currentToken(tokens, copyIndex)?.tokenType) {
            LetKeyword -> astNode.addChild(parseDeclarativeExpression(tokens, copyIndex))
            else -> throw Exception("Invalid token")//Todo change this.
        }
    }

    private fun parseDeclarativeExpression(tokens: List<Token>, copyIndex: Int): ASTNode {
        val letKeyword = parseMember(tokens, copyIndex)
        val identifierToken = consumeToken(tokens, letKeyword.second)
        when (identifierToken.first?.tokenType) {
            Identifier -> {
                val colonPair = consumeToken(tokens, identifierToken.second)
                require(isColon(colonPair.first)) { "Expected : after identifier" }
                val typeNode = currentToken(tokens, colonPair.second)
                require(isType(typeNode)) { "Expected type after identifier" }
                val continuesWithExpression= consumeToken(tokens,colonPair.second+1)
                return handlePossibleIdentifier(continuesWithExpression, identifierToken, letKeyword, typeNode, tokens)
            }

            else -> throw Exception("Expected identifier after keyword")
        }
    }

    private fun handlePossibleIdentifier(
        continuesWithExpression: Pair<Token?, Int>,
        identifierToken: Pair<Token?, Int>,
        letKeyword: Pair<ASTNode, Int>,
        typeNode: Token?,
        tokens: List<Token>
    ) = when (continuesWithExpression.first?.tokenType) {
        Assignation -> createAssignationNode(continuesWithExpression, identifierToken, letKeyword, typeNode, tokens)

        SemiColon -> createIdentifierNode(identifierToken, letKeyword, typeNode)
        //TODO handle here the possible chain of declarations
        else -> throw Exception("Expected = or ; after type")
    }

    private fun createAssignationNode(
        continuesWithExpression: Pair<Token?, Int>,
        identifierToken: Pair<Token?, Int>,
        letKeyword: Pair<ASTNode, Int>,
        typeNode: Token?,
        tokens: List<Token>
    ) = ASTNodeImpl(
        continuesWithExpression.first?.value,
        continuesWithExpression.first,
        AssignationNode,
        listOf(
            createIdentifierNode(identifierToken, letKeyword, typeNode),
            ExpressionParser(continuesWithExpression.second).parse(tokens)
        )
    )

    private fun createIdentifierNode(
        identifierToken: Pair<Token?, Int>,
        letKeyword: Pair<ASTNode, Int>,
        typeNode: Token?
    ) = ASTNodeImpl(
        identifierToken.first?.value,
        identifierToken.first,
        IdentifierNode,
        listOf(
            letKeyword.first,
            ASTNodeImpl(
                typeNode?.value, typeNode, TypeNode, null
            )
        )
    )

    private fun parseMember(tokens: List<Token>, index: Int): Pair<ASTNode, Int> {
        return when (currentToken(tokens, index)?.tokenType) {
            LetKeyword -> {
                val consumeResult = consumeToken(tokens, index)
                val token = consumeResult.first
                Pair(ASTNodeImpl(token?.value, token, KeywordNode, null), consumeResult.second)
            }

            else -> throw Exception("Invalid token")//Todo change this.
        }
    }

}