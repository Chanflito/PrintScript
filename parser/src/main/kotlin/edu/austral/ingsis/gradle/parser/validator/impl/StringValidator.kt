package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isStringToken
import edu.austral.ingsis.gradle.parser.validator.Validator

class StringValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isStringToken(it) }?.let {
            return true
        }
        return false
    }
}