package edu.austral.ingsis.gradle.parser.validator.impl
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isType
import edu.austral.ingsis.gradle.parser.validator.SyntaxValidator

class TypeValidator : SyntaxValidator {
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        return isType(currentToken(tokens,index))
    }
}