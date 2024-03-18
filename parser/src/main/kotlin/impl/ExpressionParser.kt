package impl

import Parser
import ast.ASTNodeImpl
import common.ast.*
import common.token.*
import util.*

//Here should go expressions like 5+5, 7+2
class ExpressionParser(private val startIndex: Int) : Parser {

    override fun parse(tokens: List<Token>): ASTNode {
        val indexCopy = startIndex
        return parsePrimaryOperator(tokens, indexCopy).first
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

        return left;
    }

    private fun parseSecondaryOperator(tokens: List<Token>, index: Int): Pair<ASTNode,Int>{
        var left = parseMember(tokens,index)
        while (isMultiplicativeOperator(currentToken(tokens,left.second))){
            val token = consumeToken(tokens,index)
            val right = parseMember(tokens,index)
            left = Pair(ASTNodeImpl(token.first?.value,token.first, OperatorNode, listOf(left.first, right.first)),right.second)
        }

        return left
    }
    private fun parseMember(tokens: List<Token>, index: Int): Pair<ASTNode,Int> {
        return when (val currentType = currentToken(tokens, index)?.tokenType) {
            ValueNumber -> {
                val consumeResult = consumeToken(tokens, index)
                val token = consumeResult.first
                val parsedValue = token?.value?.toDoubleOrNull()
                Pair(ASTNodeImpl(parsedValue,token,NumberNode,null),consumeResult.second)
            }
            ValueString -> {
                val consumeResult = consumeToken(tokens,index)
                val token = consumeResult.first
                Pair(ASTNodeImpl(token?.value, token, StringNode, null),consumeResult.second)
            }
            Identifier ->{
                val consumeResult=consumeToken(tokens,index)
                val token = consumeResult.first
                Pair(ASTNodeImpl(token?.value, token, IdentifierNode, null),consumeResult.second)
            }
            LeftParenthesis ->{
                val consumeResult= consumeToken(tokens,index)
                val innerExpression= parsePrimaryOperator(tokens, consumeResult.second)
                val nextToken= consumeToken(tokens,innerExpression.second)
                require(isRightParenthesis(nextToken.first)){
                    "Unexpected token: expected ) after expression"
                }
                return Pair(innerExpression.first,nextToken.second)
            }
            else -> throw Exception("Unexpected token: $currentType")
        }
    }
}