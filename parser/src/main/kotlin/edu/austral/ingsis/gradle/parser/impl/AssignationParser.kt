package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken

// Here should go assignations like a=7
class AssignationParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<ASTNode, Int> {
        val index = input.index
        val identifierToken = consumeToken(input.tokens, index)
        val identifierTokenType = getToken(identifierToken)?.tokenType
        if (identifierTokenType != Identifier) {
            throw Exception(getToken(identifierToken)?.let { ExpectedTokenErrorMessage("identifier", it).toString() })
        }

        val assignationToken = consumeToken(input.tokens, getIndex(identifierToken))
        val assignmentTokenType = getToken(assignationToken)?.tokenType
        if (assignmentTokenType != Assignation) {
            throw Exception(getToken(assignationToken)?.let { ExpectedTokenErrorMessage("assignation", it).toString() })
        }

        return Pair(
            ASTNodeImpl(
                "=",
                getToken(assignationToken),
                AssignationNode,
                listOf(
                    ASTNodeImpl(getToken(identifierToken)?.value, getToken(identifierToken), IdentifierNode, listOf()),
                    ExpressionParser().parse(InputContext(input.tokens, getIndex(assignationToken))).first,
                ),
            ),
            getIndex(assignationToken),
        )
    }

    private fun getIndex(assignationToken: Pair<Token?, Int>) = assignationToken.second

    private fun getToken(identifierToken: Pair<Token?, Int>) = identifierToken.first
}
