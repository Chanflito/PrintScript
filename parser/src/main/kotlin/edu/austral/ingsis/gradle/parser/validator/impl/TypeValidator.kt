package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isType
import edu.austral.ingsis.gradle.parser.validator.Validator

class TypeValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isType(it) }?.let {
            return true
        }
        return false
    }

}
