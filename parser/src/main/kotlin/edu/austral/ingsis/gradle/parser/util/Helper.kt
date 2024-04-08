package edu.austral.ingsis.gradle.parser.util

import edu.austral.ingsis.gradle.common.token.*
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.impl.OperationParser
import edu.austral.ingsis.gradle.parser.impl.ValueNumberParser
import edu.austral.ingsis.gradle.parser.validator.impl.NumberValidator
import edu.austral.ingsis.gradle.parser.validator.impl.OperationValidator
import edu.austral.ingsis.gradle.parser.validator.impl.StringValidator

fun endOfFile(
    tokens: List<Token>,
    currentIndex: Int,
): Boolean {
    return currentIndex > (tokens.size - 1)
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

fun isKeyword(token: Token?): Boolean {
    return token != null && token.tokenType in setOf(LetKeyword)
}

fun isIdentifier(token: Token?): Boolean {
    return token != null && token.tokenType == Identifier
}

fun isType(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(NumberType, StringType))
}

fun isValue(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(NumberValue, StringValue))
}

fun isOperator(token: Token?): Boolean {
    return (token != null && token.tokenType in setOf(Plus, Minus, Multiply, Divide))
}

fun isNumberToken(token: Token?): Boolean {
    return token != null && token.tokenType == NumberValue
}

fun isStringToken(token: Token?): Boolean {
    return token != null && token.tokenType == StringValue
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

fun currentToken(
    list: List<Token>,
    index: Int,
): Token? {
    return if (index < list.size) list[index] else null
}

// Returns new index and the last token consumed.
fun consumeToken(
    list: List<Token>,
    index: Int,
): Pair<Token?, Int> {
    val current = currentToken(list, index)
    val nextIndex = index + 1
    return Pair(current, nextIndex)
}

fun getSublists(list: List<Token>, index: Int): Pair<List<Token>, List<Token>> {
    val leftSide = list.subList(0, index)
    val rightSide = list.subList(index + 1, list.size)
    return Pair(leftSide, rightSide)
}

fun createValueNumberParser(): Parser {
    return ValueNumberParser(NumberValidator())
}

fun createValueStringParser(): Parser {
    return ValueNumberParser(StringValidator())
}

fun createPlusOperationParser(): Parser {
    return OperationParser(Plus, OperationValidator(), listOf(createValueNumberParser(), createValueStringParser()))
}

fun createMinusOperationParser(): Parser {
    return OperationParser(Minus, OperationValidator(), listOf(createValueNumberParser(), createValueStringParser()))
}

fun createMultiplyOperationParser(): Parser {
    return OperationParser(Multiply, OperationValidator(), listOf(createValueNumberParser(), createValueStringParser()))
}

fun createDivideOperationParser(): Parser {
    return OperationParser(Divide, OperationValidator(), listOf(createValueNumberParser(), createValueStringParser()))
}
