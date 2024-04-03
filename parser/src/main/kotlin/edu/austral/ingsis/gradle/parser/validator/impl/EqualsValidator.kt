package edu.austral.ingsis.gradle.parser.validator.impl
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isAssignation
import edu.austral.ingsis.gradle.parser.validator.SyntaxValidator

class EqualsValidator : SyntaxValidator {
    override fun validate(
        tokens: List<Token>,
        index: Int,
    ): Boolean {
        return isAssignation(currentToken(tokens, index))
    }
}
