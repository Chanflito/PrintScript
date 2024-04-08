package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isSemiColon
import edu.austral.ingsis.gradle.parser.validator.Validator

class SemiColonValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isSemiColon(it) }?.let {
            return true
        }
        return false
    }

}
