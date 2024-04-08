package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isAssignation
import edu.austral.ingsis.gradle.parser.validator.Validator

class EqualsValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isAssignation(it) }?.let {
            return true
        }
        return false
    }
}
