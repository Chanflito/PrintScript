package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.validator.CompositeValidator
import edu.austral.ingsis.gradle.parser.validator.Validator

class OrValidator(override val validators: List<Validator>) : CompositeValidator {
    override fun validate(tokens: List<Token>): Boolean {
        return validators.any { it.validate(tokens) }
    }
}