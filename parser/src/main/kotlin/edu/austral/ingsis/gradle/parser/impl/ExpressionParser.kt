package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.parser.util.*
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.token.Token

//Here should go expressions like 5+5, 7+2
//Here should go expressions like 5+5, 7+2
class ExpressionParser (private val parsers: Map<TokenType, Parser<InputContext>> = EXPRESSION_PARSER_MAP):
    Parser<InputContext> {

    override fun parse(input: InputContext): Pair<ASTNode,Int> {
        val indexCopy = input.index
        return parsePrimaryOperator(input.tokens, indexCopy)
    }


    /*
    PrimaryOperators are the ones that are evaluated first, like 5+5*2, the multiplication is the primary operator
    SecondaryOperators are the ones that are evaluated second, like 5+5*2, the addition is the secondary operator
     */

    private fun parsePrimaryOperator(tokens: List<Token>, index: Int): Pair<ASTNode,Int> {
        var left = parseSecondaryOperator(tokens,index)
        while (isAdditiveOperator(currentToken(tokens,left.second))){
            val token = consumeToken(tokens,left.second)
            val right = parseSecondaryOperator(tokens,token.second)
            left =  Pair(ASTNodeImpl(token.first?.value,token.first, OperatorNode, listOf(left.first, right.first)),right.second)
        }

        return left
    }

    private fun parseSecondaryOperator(tokens: List<Token>, index: Int): Pair<ASTNode,Int>{
        var left = parseMember(tokens,index)
        while (isMultiplicativeOperator(currentToken(tokens,left.second))){
            val token = consumeToken(tokens,left.second)
            val right = parseMember(tokens,token.second)
            left = Pair(ASTNodeImpl(token.first?.value,token.first, OperatorNode, listOf(left.first, right.first)),right.second)
        }
        return left
    }
    private fun parseMember(tokens: List<Token>, index: Int): Pair<ASTNode, Int> {
        val currentToken = currentToken(tokens, index) ?: throw Exception(NoTokenFoundErrorMessage(index).toString())
        val parserFound = parsers[currentToken.tokenType] ?: throw Exception(InvalidTokenErrorMessage(currentToken).toString())
        return parserFound.parse(InputContext(tokens, index))
    }

}