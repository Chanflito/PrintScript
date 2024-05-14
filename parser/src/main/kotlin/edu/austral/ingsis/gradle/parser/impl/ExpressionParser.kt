package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.factory.EXPRESSION_PARSER_MAP
import edu.austral.ingsis.gradle.parser.operation.Operation
import edu.austral.ingsis.gradle.parser.operation.PRIMARY_OPERATIONS_MAP
import edu.austral.ingsis.gradle.parser.operation.SECONDARY_OPERATIONS_MAP
import edu.austral.ingsis.gradle.parser.util.InvalidOperatorException
import edu.austral.ingsis.gradle.parser.util.NoParserFoundException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.endOfFile
import edu.austral.ingsis.gradle.parser.util.isAdditiveOperator
import edu.austral.ingsis.gradle.parser.util.isMultiplicativeOperator

class ExpressionParser(
    private val parsers: Map<TokenType, Parser<InputContext>> = EXPRESSION_PARSER_MAP,
    private val primaryOperators: Map<TokenType, Operation> = PRIMARY_OPERATIONS_MAP,
    private val secondaryOperators: Map<TokenType, Operation> = SECONDARY_OPERATIONS_MAP,
) : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val indexCopy = input.index
        return parsePrimaryOperator(input.tokens, indexCopy)
    }

    // PrimaryOperators are the ones that are evaluated first, like 5+5*2, the multiplication is the primary operator
    private fun parsePrimaryOperator(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        var left = parseSecondaryOperator(tokens, index)
        while (!endOfFile(tokens, left.second) && isAdditiveOperator(currentToken(tokens, left.second))) {
            val (token, next) = consumeToken(tokens, left.second)
            val right = parseSecondaryOperator(tokens, next)
            val operation = primaryOperators[token.tokenType] ?: throw InvalidOperatorException(token)
            left = Pair(operation.execute(left.first, right.first, token), right.second)
        }

        return left
    }

    //  SecondaryOperators are the ones that are evaluated second, like 5+5*2, the addition is the secondary operator
    private fun parseSecondaryOperator(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        var left = parseMember(tokens, index)
        while (!endOfFile(tokens, left.second) && isMultiplicativeOperator(currentToken(tokens, left.second))) {
            val (token, next) = consumeToken(tokens, left.second)
            val right = parseMember(tokens, next)
            val operation = secondaryOperators[token.tokenType] ?: throw InvalidOperatorException(token)
            left = Pair(operation.execute(left.first, right.first, token), right.second)
        }
        return left
    }

    private fun parseMember(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        val currentToken = currentToken(tokens, index)
        val parserFound = parsers[currentToken.tokenType] ?: throw NoParserFoundException(currentToken)
        return parserFound.parse(InputContext(tokens, index))
    }
}
