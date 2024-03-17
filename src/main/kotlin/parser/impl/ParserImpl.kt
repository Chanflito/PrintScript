package parser.impl

import common.ast.*
import common.token.*
import parser.Parser
import parser.util.*

//posibles errores sintacticos


class ParserImpl(): Parser {
    private var index = 0;
    override fun parse(tokens: List<Token>): List<ASTNode> {
        val ast = ArrayList<ASTNode>()
        while (!endOfFile(tokens, index)){
            val currentToken = currentToken(tokens)
            if(isSemiColon(currentToken)){
                consumeToken(tokens);
                continue;
            }
            ast.add(parsePrintLn(tokens));
        }
        return ast;

    }

    private fun parsePrintLn(tokens: List<Token>): ASTNode{
        if (isPrintLn(currentToken(tokens))){
            val printLnToken = consumeToken(tokens);
//            check if after a println call there is a left parenthesis
//            if(!isLeftParenthesis(currentToken(tokens))){
//                throw Exception("Missing ( after method call")
//            }
            val childrenExpression = parsePrimaryExpression(tokens)
            return ASTNodeImpl("println", printLnToken, PrintLnNode, listOf(childrenExpression))
        }
        return parseEqualsExpression(tokens);
    }


    private fun parseEqualsExpression(): ASTNode{
        var left = parseExpression();
        while (currentToken()?.tokenType == TokenType.ASSIGNATION){
            val token = consumeToken();
            val right = parseExpression();
            left = ASTNodeImpl(token, NodeType.ASSIGNMENT_NODE, listOf(left, right))
        }
        return left;
    }

    private fun parseExpression(tokens: List<Token>): ASTNode{
        if(isLetKeyword(currentToken(tokens))){
            return parseAssignmentExpression(tokens);
        }
        return parseAdditiveExpression(tokens);
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

    private fun parseAssignmentExpression(): ASTNode{
        val keyword = parsePrimaryExpression();
        val variable = consumeToken();
        if (isIdentifier(variable)){
            val colon = consumeToken();
            if (isColon(colon)){
                throw Exception("Expected : after identifier")
            }
            val type = currentToken();
            if(isAType(type)){
                throw Exception("Expected type after identifier")
            }
            return ASTNodeImpl(variable, NodeType.IDENTIFIER_NODE, listOf(keyword, parsePrimaryExpression()))

        }
        throw Exception("Expected identifier after keyword");
    }

    private fun parseMultiplicativeExpression(tokens: List<Token>): ASTNode{
        var left = parsePrimaryExpression(tokens);
        while (isMultiplicativeOperator(currentToken(tokens))){
            val token = consumeToken(tokens);
            val right = parsePrimaryExpression(tokens);
            left =  ASTNodeImpl(token?.value,token,OperatorNode, listOf(left, right))
        }

        return left;
    }

    private fun parsePrimaryExpression(tokens: List<Token>): ASTNode {
        return when (val currentType = currentToken(tokens)?.tokenType) {
            ValueNumber -> {
                val token = consumeToken(tokens)
                val parsedValue = token?.value?.toIntOrNull()
                ASTNodeImpl(parsedValue, token, ValueNode, null)
            }
            ValueString -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, StringNode, null)
            }
            LetKeyword -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, KeywordNode, null)
            }
            TypeNumber -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, TypeNode, null)
            }
            TypeString -> {
                val token = consumeToken(tokens)
                ASTNodeImpl(token?.value, token, TypeNode, null)
            }
            LeftParenthesis -> {
                consumeToken(tokens)
                val innerExpression = parseExpression(tokens)
                consumeToken(tokens)
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