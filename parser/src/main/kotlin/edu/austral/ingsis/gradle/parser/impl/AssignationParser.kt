package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isAssignation
import edu.austral.ingsis.gradle.parser.util.isIdentifier

// Parser for assignations of type a=7
class AssignationParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (tokens, index) = input
        val (identifier, assignationIndex) = consumeToken(tokens, index)

        if (!isIdentifier(identifier)) {
            throw MissingTokenException(identifier, "identifier")
        }
        val identifierNode = IdentifierNode(identifier.value, identifier.tokenPosition)

        val (assignation, expressionIndex) = consumeToken(tokens, assignationIndex)

        if (!isAssignation(assignation)) {
            throw MissingTokenException(assignation, "assignation")
        }
        val expressionResult = ExpressionParser().parse(InputContext(tokens, expressionIndex))

        return Pair(
            ReAssignationNode(
                assignation.tokenPosition,
                expressionResult.first as Expression,
                identifierNode,
            ),
            expressionResult.second,
        )
    }
}
