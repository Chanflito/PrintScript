package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isValue
import edu.austral.ingsis.gradle.parser.validator.Validator

class LiteralValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isValue(it) }?.let {
            return true
        }
        return false
    }
}