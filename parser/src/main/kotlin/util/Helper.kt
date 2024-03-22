package util

import Parser
import common.token.*
import impl.*

fun endOfFile(tokens: List<Token>, currentIndex: Int): Boolean {
    return currentIndex > (tokens.size - 1);
}

fun isColon(token: Token?): Boolean {
    return token != null && token.tokenType == Colon
}

fun isSemiColon(token: Token?): Boolean {
    return token != null && token.tokenType == SemiColon
}

fun isAssignation(token: Token?): Boolean {
    return token != null && token.tokenType == Assignation
}

fun isLetKeyword(token: Token?): Boolean {
    return token != null && token.tokenType == LetKeyword
}

fun isIdentifier(token: Token?): Boolean {
    return token != null && token.tokenType == Identifier
}

fun isType(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(TypeNumber, TypeString))
}

fun isValue(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(ValueNumber, ValueString))
}

fun isOperator(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(Plus, Minus, Multiply, Divide))
}

fun isAdditiveOperator(token: Token?): Boolean{
    return token != null && token.tokenType in setOf(Plus, Minus)
}

fun isMultiplicativeOperator(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(Multiply, Divide))
}

fun isLeftParenthesis(token: Token?): Boolean {
    return token != null && token.tokenType == LeftParenthesis
}

fun isRightParenthesis(token: Token?): Boolean {
    return token != null && token.tokenType == RightParenthesis
}

fun isParenthesis(token: Token?): Boolean {
    return token != null && token.tokenType in setOf(RightParenthesis, LeftParenthesis)
}

fun isPrintLn(token: Token?):Boolean{
    return token != null && token.tokenType == PrintlnKeyword
}

fun currentToken(list: List<Token>,index: Int): Token? {
    return if (index < list.size) list[index] else null
}
//Returns new index and the last token consumed.
fun consumeToken(list: List<Token>, index: Int): Pair<Token?, Int>{
    val current = currentToken(list,index)
    val nextIndex = index + 1;
    return Pair(current, nextIndex)
}

fun createComposeParser() : ComposeParser {
    return ComposeParser(
        mapOf(
            PrintlnKeyword to PrintlnParser(),
            ValueString to ExpressionParser(),
            ValueNumber to ExpressionParser(),
            LeftParenthesis to ExpressionParser(),
            LetKeyword to DeclarationParser(),
            Identifier to AssignationParser(),
        ))
}