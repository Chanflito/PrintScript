package edu.austral.ingsis.gradle.parser.validator.impl
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isSemiColon
import edu.austral.ingsis.gradle.parser.validator.SyntaxValidator

class SemiColonValidator : SyntaxValidator {
    override fun validate(
        tokens: List<Token>,
        index: Int,
    ): Boolean {
        return isSemiColon(currentToken(tokens, index))
    }
}
