package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isNumberToken
import edu.austral.ingsis.gradle.parser.validator.Validator

class NumberValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isNumberToken(it) }?.let {
            return true
        }
        return false
    }
}