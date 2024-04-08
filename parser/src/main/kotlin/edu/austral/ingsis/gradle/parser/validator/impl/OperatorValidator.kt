package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.util.isOperator
import edu.austral.ingsis.gradle.parser.validator.Validator

class OperatorValidator : Validator {
    override fun validate(tokens: List<Token>): Boolean {
        tokens.find { isOperator(it) }?.let {
            return true
        }
        return false
    }
}