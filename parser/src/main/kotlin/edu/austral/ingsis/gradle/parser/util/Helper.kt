package edu.austral.ingsis.gradle.parser.util

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.BooleanType
import edu.austral.ingsis.gradle.common.token.BooleanValue
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.ConstKeyword
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.ElseKeyword
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.LeftBrace
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.Minus
import edu.austral.ingsis.gradle.common.token.Multiply
import edu.austral.ingsis.gradle.common.token.NumberType
import edu.austral.ingsis.gradle.common.token.NumberValue
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.RightBrace
import edu.austral.ingsis.gradle.common.token.RightParenthesis
import edu.austral.ingsis.gradle.common.token.SemiColon
import edu.austral.ingsis.gradle.common.token.StringType
import edu.austral.ingsis.gradle.common.token.StringValue
import edu.austral.ingsis.gradle.common.token.Token

fun endOfFile(
    tokens: List<Token>,
    currentIndex: Int,
): Boolean {
    return currentIndex >= tokens.size
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

fun isConstKeyword(token: Token?): Boolean {
    return token != null && token.tokenType == ConstKeyword
}

fun isIdentifier(token: Token?): Boolean {
    return token != null && token.tokenType == Identifier
}

fun isType(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(NumberType, StringType, BooleanType))
}

fun isValue(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(NumberValue, StringValue))
}

fun isNumberValue(token: Token?): Boolean {
    return token != null && token.tokenType == NumberValue
}

fun isStringValue(token: Token?): Boolean {
    return token != null && token.tokenType == StringValue
}

fun isBooleanValue(token: Token?): Boolean {
    return token != null && token.tokenType == BooleanValue
}

fun isOperator(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(Plus, Minus, Multiply, Divide))
}

fun isAdditiveOperator(token: Token?): Boolean {
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

fun isPrintLn(token: Token?): Boolean {
    return token != null && token.tokenType == PrintlnKeyword
}

fun isLeftBrace(token: Token?): Boolean {
    return token != null && token.tokenType == LeftBrace
}

fun isRightBrace(token: Token?): Boolean {
    return token != null && token.tokenType == RightBrace
}

fun isElseKeyword(token: Token?): Boolean {
    return token != null && token.tokenType == ElseKeyword
}

fun currentToken(
    list: List<Token>,
    index: Int,
): Token {
    if (endOfFile(list, index)) throw EndOfFileException()
    return list[index]
}

// Returns new index and the last token consumed.
fun consumeToken(
    list: List<Token>,
    index: Int,
): Pair<Token, Int> {
    val current = currentToken(list, index)
    val nextIndex = index + 1
    return Pair(current, nextIndex)
}
