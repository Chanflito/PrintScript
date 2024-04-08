package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isKeyword
import edu.austral.ingsis.gradle.parser.validator.Validator

class KeywordValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isKeyword(it) }?.let {
            return true
        }
        return false
    }


}
