package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.Minus
import edu.austral.ingsis.gradle.common.token.Multiply
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.EXPRESSION_PARSER_MAP
import edu.austral.ingsis.gradle.parser.util.InvalidTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.NoTokenFoundErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isAdditiveOperator
import edu.austral.ingsis.gradle.parser.util.isMultiplicativeOperator

// Here should go expressions like 5+5, 7+2
// Here should go expressions like 5+5, 7+2
class ExpressionParser(private val parsers: Map<TokenType, Parser<InputContext>> = EXPRESSION_PARSER_MAP) :
    Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val indexCopy = input.index
        return parsePrimaryOperator(input.tokens, indexCopy)
    }

    /*
    PrimaryOperators are the ones that are evaluated first, like 5+5*2, the multiplication is the primary operator
    SecondaryOperators are the ones that are evaluated second, like 5+5*2, the addition is the secondary operator
     */

    private fun parsePrimaryOperator(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        var left = parseSecondaryOperator(tokens, index)
        while (isAdditiveOperator(currentToken(tokens, left.second))) {
            val tokenPair = consumeToken(tokens, left.second)
            val right = parseSecondaryOperator(tokens, tokenPair.second)
            val token = tokenPair.first ?: throw Exception(NoTokenFoundErrorMessage(tokenPair.second).toString())
            when (token.tokenType) {
                Plus ->
                    left =
                        Pair(
                            SumNode(
                                token.tokenPosition,
                                left.first as Expression,
                                right.first as Expression,
                            ),
                            right.second,
                        )

                Minus -> {
                    if (checkIfChildrenAreString(right.first, left.first)) {
                        throw Exception("Cannot perform subtraction operation over strings")
                    }
                    left =
                        Pair(
                            SubtractNode(
                                token.tokenPosition,
                                left.first as Expression,
                                right.first as Expression,
                            ),
                            right.second,
                        )
                }
            }
        }

        return left
    }

    private fun parseSecondaryOperator(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        var left = parseMember(tokens, index)
        while (isMultiplicativeOperator(currentToken(tokens, left.second))) {
            val tokenPair = consumeToken(tokens, left.second)
            val right = parseMember(tokens, tokenPair.second)
            val token = tokenPair.first ?: throw Exception(NoTokenFoundErrorMessage(tokenPair.second).toString())
            when (token.tokenType) {
                Multiply -> {
                    if (checkIfChildrenAreString(right.first, left.first)) {
                        throw Exception("Cannot perform multiplication operation over strings")
                    }
                    left =
                        Pair(
                            MultiplyNode(
                                token.tokenPosition,
                                left.first as Expression,
                                right.first as Expression,
                            ),
                            right.second,
                        )
                }

                Divide -> {
                    if (checkIfChildrenAreString(right.first, left.first)) {
                        throw Exception("Cannot perform division operation over strings")
                    }
                    left =
                        Pair(
                            DivideNode(
                                token.tokenPosition,
                                left.first as Expression,
                                right.first as Expression,
                            ),
                            right.second,
                        )
                }
            }
        }
        return left
    }

    private fun parseMember(
        tokens: List<Token>,
        index: Int,
    ): Pair<AST, Int> {
        val currentToken = currentToken(tokens, index) ?: throw Exception(NoTokenFoundErrorMessage(index).toString())
        val parserFound =
            parsers[currentToken.tokenType] ?: throw Exception(InvalidTokenErrorMessage(currentToken).toString())
        return parserFound.parse(InputContext(tokens, index))
    }

    private fun checkIfChildrenAreString(
        right: AST,
        left: AST,
    ): Boolean {
        return right is StringLiteral || left is StringLiteral
    }
}
