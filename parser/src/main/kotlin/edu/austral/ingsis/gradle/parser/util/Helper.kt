package edu.austral.ingsis.gradle.parser.util

import common.token.*

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


