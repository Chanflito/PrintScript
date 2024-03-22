package impl

import Parser
import InputContext
import ast.*
import common.ast.*
import common.token.*
import util.*

//Here should go assignations like let a : number= 7; only with let keyword
class DeclarationParser : Parser<InputContext> {// This parser is so long
    override fun parse(input: InputContext): Pair<ASTNode,Int> {
        val copyIndex = input.index
        val token=currentToken(input.tokens, copyIndex) ?: throw Exception(NoTokenFoundErrorMessage(copyIndex).toString())
        return when (token.tokenType) {
            LetKeyword -> parseDeclarativeExpression(input.tokens, copyIndex)
            else -> throw Exception(ExpectedTokenErrorMessage("let", token).toString())
        }
    }

    private fun parseDeclarativeExpression(tokens: List<Token>, copyIndex: Int): Pair<ASTNode,Int> {
        val letKeyword = parseMember(tokens, copyIndex)
        val identifierPair = consumeToken(tokens, letKeyword.second)
        val identifierToken= identifierPair.first ?: throw Exception(NoTokenFoundErrorMessage(identifierPair.second).toString())
        val identifierIndex= identifierPair.second
        when (identifierToken.tokenType) {
            Identifier -> {
                val colonPair = consumeToken(tokens, identifierIndex)
                verifyIfColonExists(colonPair)
                val typeToken = currentToken(tokens, colonPair.second)
                verifyIfTypeExists(typeToken, colonPair.second)
                val continuesWithExpression= consumeToken(tokens,colonPair.second+1)
                return handlePossibleIdentifier(continuesWithExpression, identifierPair, letKeyword, typeToken, tokens)
            }

            else -> throw Exception(ExpectedTokenErrorMessage("identifier", identifierToken).toString())
        }
    }

    private fun verifyIfColonExists(pair: Pair<Token?, Int>) {
        val colonToken= pair.first;
        require(isColon(colonToken)) {
            when {
                colonToken == null -> throw Exception(NoTokenFoundErrorMessage(pair.second).toString())
                else -> throw Exception(ExpectedTokenErrorMessage(")", colonToken).toString())
            }
        }
    }

    private fun verifyIfTypeExists(typeToken: Token?, index: Int) {
        require(isType(typeToken)) {
            when {
                typeToken == null -> throw Exception(NoTokenFoundErrorMessage(index).toString())
                else -> throw Exception(ExpectedTokenErrorMessage(")", typeToken).toString())
            }
        }
    }

    private fun handlePossibleIdentifier(
        continuesWithExpression: Pair<Token?, Int>,
        identifierPair: Pair<Token?, Int>,
        letKeyword: Pair<ASTNode, Int>,
        typeNode: Token?,
        tokens: List<Token>
    ) : Pair<ASTNode,Int> = when (continuesWithExpression.first?.tokenType) {
        Assignation -> createAssignationNode(continuesWithExpression, identifierPair, letKeyword, typeNode, tokens)

        SemiColon -> createIdentifierNode(identifierPair, letKeyword, typeNode)
        else -> {
            val possibleTokenFound=continuesWithExpression.first
            if (possibleTokenFound== null) {
                throw Exception(NoTokenFoundErrorMessage(continuesWithExpression.second).toString())
            } else {
                throw Exception(ExpectedTokenErrorMessage("; or =", possibleTokenFound).toString())
            }
        }
    }

    private fun createAssignationNode(
        continuesWithExpression: Pair<Token?, Int>,
        identifierToken: Pair<Token?, Int>,
        letKeyword: Pair<ASTNode, Int>,
        typeNode: Token?,
        tokens: List<Token>) : Pair<ASTNode,Int> =Pair(ASTNodeImpl(
        continuesWithExpression.first?.value,
        continuesWithExpression.first,
        AssignationNode,
        listOf(
            createIdentifierNode(identifierToken, letKeyword, typeNode).first,
            ExpressionParser().parse(InputContext(tokens,continuesWithExpression.second)).first
        )),
        continuesWithExpression.second)

    private fun createIdentifierNode(
        identifierToken: Pair<Token?, Int>,
        letKeyword: Pair<ASTNode, Int>,
        typeNode: Token?
    ) : Pair<ASTNode,Int> = Pair(ASTNodeImpl(
        identifierToken.first?.value,
        identifierToken.first,
        IdentifierNode,
        listOf(
            letKeyword.first,
            ASTNodeImpl(
                typeNode?.value, typeNode, TypeNode, null
            )
        )), identifierToken.second+1
    )

    private fun parseMember(tokens: List<Token>, index: Int): Pair<ASTNode, Int> {
        val currentToken= currentToken(tokens, index) ?: throw Exception(NoTokenFoundErrorMessage(index).toString())
        return when (currentToken.tokenType) {
            LetKeyword -> {
                val consumeResult = consumeToken(tokens, index)
                val token = consumeResult.first
                Pair(ASTNodeImpl(token?.value, token, KeywordNode, null), consumeResult.second)
            }

            else -> throw Exception(ExpectedTokenErrorMessage("let", currentToken).toString())
        }
    }

}