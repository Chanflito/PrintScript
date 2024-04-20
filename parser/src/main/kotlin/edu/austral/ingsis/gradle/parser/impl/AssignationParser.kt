package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isAssignation
import edu.austral.ingsis.gradle.parser.util.isIdentifier

// Here should go assignations like a=7
class AssignationParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val index = input.index
        val identifierToken = consumeToken(input.tokens, index)
        val identifier = getToken(identifierToken) ?: throw Exception("No token found")

        if (!isIdentifier(identifier)) {
            throw Exception(ExpectedTokenErrorMessage("identifier", identifier).toString())
        }
        val identifierNode = IdentifierNode(identifier.value, identifier.tokenPosition)

        val assignationToken = consumeToken(input.tokens, getIndex(identifierToken))
        val assignation = getToken(assignationToken) ?: throw Exception("No token found")

        if (!isAssignation(assignation)) {
            throw Exception(ExpectedTokenErrorMessage("assignation", assignation).toString())
        }
        val expressionResult = ExpressionParser().parse(InputContext(input.tokens, getIndex(assignationToken)))
        return Pair(
            ReAssignationNode(
                assignation.tokenPosition,
                expressionResult.first as Expression,
                identifierNode,
            ),
            expressionResult.second,
        )
    }

    private fun getIndex(assignationToken: Pair<Token?, Int>) = assignationToken.second

    private fun getToken(identifierToken: Pair<Token?, Int>) = identifierToken.first
}
