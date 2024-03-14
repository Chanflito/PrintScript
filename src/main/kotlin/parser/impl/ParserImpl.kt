package parser.impl

import common.ast.ASTNode
import common.ast.ASTNodeImpl
import common.ast.NodeType
import common.token.Token
import common.token.TokenType
import parser.Parser

class ParserImpl(private val tokens: List<Token>): Parser {
    private var index = 0;

    override fun parse(): List<ASTNode> {
        var ast = ArrayList<ASTNode>()
        while (notEndOfLine()){
            if(currentToken()?.tokenType == TokenType.SEMI_COLON){
                consumeToken();
                continue;
            }
            ast.add(parseEqualsExpression());
        }
        return ast;

    }

    private fun parseExpression(): ASTNode{
        if(currentToken()?.tokenType != TokenType.LET_KEYWORD){
            return parseAdditiveExpression();
        }
        return parseAssignmentExpression();
    }

    private fun parsePrimaryExpression(): ASTNode {
        return when (val currentType = currentToken()?.tokenType) {
            TokenType.VALUE_NUMBER -> ASTNodeImpl(consumeToken(), NodeType.NUMBER_NODE, null)
            TokenType.VALUE_STRING -> ASTNodeImpl(consumeToken(), NodeType.STRING_NODE, null)
            TokenType.LET_KEYWORD -> ASTNodeImpl(consumeToken(), NodeType.KEYWORD_NODE, null)
            TokenType.TYPE_NUMBER -> ASTNodeImpl(consumeToken(), NodeType.TYPE_NODE, null)
            TokenType.TYPE_STRING -> ASTNodeImpl(consumeToken(), NodeType.TYPE_NODE, null)
            else -> throw Exception("Unexpected token: $currentType")
        }
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

    private fun isAType(type: Token?) = type?.tokenType !in setOf(TokenType.TYPE_NUMBER, TokenType.TYPE_STRING)

    private fun isColon(colon: Token?) = colon?.tokenType != TokenType.COLON

    private fun isIdentifier(variable: Token?) = variable?.tokenType == TokenType.IDENTIFIER

    private fun parseAdditiveExpression(): ASTNode{
        var left = parseMultiplicativeExpression();

        while (currentToken()?.tokenType == TokenType.PLUS || currentToken()?.tokenType == TokenType.MINUS){
            val token = consumeToken()
            val right = parseMultiplicativeExpression();
            left =  ASTNodeImpl(token, NodeType.OPERATOR_NODE, listOf(left, right))
        }

        return left;
    }

    private fun parseMultiplicativeExpression(): ASTNode{
        var left = parsePrimaryExpression();
        while (currentToken()?.tokenType == TokenType.MULTIPLY || currentToken()?.tokenType == TokenType.DIVIDE){
            val token = consumeToken();
            val right = parsePrimaryExpression();
            left =  ASTNodeImpl(token,NodeType.OPERATOR_NODE, listOf(left, right))
        }

        return left;
    }

    private fun notEndOfLine(): Boolean{
        return currentToken() != null && index < tokens.size;
    }

    private fun currentToken(): Token? {
        return if (index < tokens.size) tokens[index] else null
    }

    private fun incrementIndex(){
        index++
    }

    private fun consumeToken(): Token?{
        val token = currentToken();
        incrementIndex();
        return token;
    }

}