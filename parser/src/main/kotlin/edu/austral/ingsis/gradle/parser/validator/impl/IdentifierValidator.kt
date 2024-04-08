package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isIdentifier
import edu.austral.ingsis.gradle.parser.validator.Validator

class IdentifierValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isIdentifier(it) }?.let {
            return true
        }
        return false
    }
}
