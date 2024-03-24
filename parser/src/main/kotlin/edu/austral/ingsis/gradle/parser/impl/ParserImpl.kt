package edu.austral.ingsis.gradle.parser.impl

import common.ast.*
import common.token.*
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.common.ast.*
import edu.austral.ingsis.gradle.parser.util.*

class ParserImpl(): Parser {
    private var index = 0;
    override fun parse(tokens: List<Token>): ASTNode {
        var astNode= ASTNodeImpl("Program",null, ProgramNode, listOf())
        while (!endOfFile(tokens, index)){
            val currentToken = currentToken(tokens)
            if(isSemiColon(currentToken)){
                consumeToken(tokens);
                continue;
            }
            astNode = astNode.addChild(parseStatement(tokens));
        }
        return astNode

    }
    private fun parseStatement(tokens: List<Token>): ASTNode{
        return when(currentToken(tokens)?.tokenType){
            PrintlnKeyword -> parsePrintLn(tokens)
            else -> parseEqualsExpression(tokens)
        };
    }

    private fun parsePrintLn(tokens: List<Token>): ASTNode{
            val printLnToken = consumeToken(tokens);
//          check if after a println call there is a left parenthesis
            require(isLeftParenthesis(currentToken(tokens))){"Missing ( after method call"}

            val childrenExpression = parsePrimaryExpression(tokens)
            return ASTNodeImpl("println", printLnToken, PrintLnNode, listOf(childrenExpression))
    }



    private fun parseEqualsExpression(tokens: List<Token>): ASTNode{
        var left = parseExpression(tokens);
        while (isAssignation(currentToken(tokens))){
            val token = consumeToken(tokens);
            val right = parseExpression(tokens);
            left = ASTNodeImpl("=",token, AssignationNode, listOf(left, right))
        }
        return left;
    }

    private fun parseExpression(tokens: List<Token>): ASTNode{
        return when(currentToken(tokens)?.tokenType){
            LetKeyword -> parseDeclarativeExpression(tokens)
            else -> parseAdditiveExpression(tokens)
        };
    }


    private fun parseAdditiveExpression(tokens: List<Token>): ASTNode{
        var left = parseMultiplicativeExpression(tokens);
        while (isAdditiveOperator(currentToken(tokens))){
            val token = consumeToken(tokens)
            val right = parseMultiplicativeExpression(tokens);
            left =  ASTNodeImpl(token?.value,token, OperatorNode, listOf(left, right))
        }

        return left;
    }

    private fun parseDeclarativeExpression(tokens: List<Token>): ASTNode{
        val keyword = parsePrimaryExpression(tokens);
        val variable = consumeToken(tokens);
        when(variable?.tokenType){
            Identifier -> {
                val colon = consumeToken(tokens);
                require(isColon(colon)){ "Expected : after identifier" }
                val type = currentToken(tokens);
                require(isType(type)){"Expected type after identifier" }
                return ASTNodeImpl(variable.value,variable, IdentifierNode, listOf(keyword, parsePrimaryExpression(tokens)))
            }
            else -> throw Exception("Expected identifier after keyword");
        }

    }

    private fun parseMultiplicativeExpression(tokens: List<Token>): ASTNode{
        var left = parsePrimaryExpression(tokens);
        while (isMultiplicativeOperator(currentToken(tokens))){
            val token = consumeToken(tokens);
            val right = parsePrimaryExpression(tokens);
            left =  ASTNodeImpl(token?.value,token, OperatorNode, listOf(left, right))
        }

        return left;
    }

    private fun parsePrimaryExpression(tokens: List<Token>): ASTNode {
        return when (val currentType = currentToken(tokens)?.tokenType) {
            ValueNumber -> {
                val token = consumeToken(tokens)
                val parsedValue = token?.value?.toDoubleOrNull();
                ASTNodeImpl(parsedValue, token, NumberNode, emptyList())
            }
            ValueString -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, StringNode, emptyList())
            }
            LetKeyword -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, KeywordNode, emptyList())
            }
            TypeNumber -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, TypeNode, emptyList())
            }
            TypeString -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, TypeNode, emptyList())
            }
            Identifier -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, IdentifierNode, emptyList())
            }
            LeftParenthesis -> {
                consumeToken(tokens)
                val innerExpression = parseExpression(tokens)
                require(isRightParenthesis(consumeToken(tokens))){
                    "Unexpected token: expected ) after expression"
                }
                return innerExpression
            }
            else -> throw Exception("Unexpected token: $currentType")
        }
    }

    private fun currentToken(list: List<Token>):Token?{
        return if (index < list.size) list[index] else null
    }

    private fun consumeToken(list: List<Token>): Token?{
        val current = currentToken(list)
        index++
        return current;
    }
}