package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isColon
import edu.austral.ingsis.gradle.parser.validator.Validator

class ColonValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isColon(it) }?.let {
            return true
        }
        return false
    }

}
