package edu.austral.ingsis.gradle.parser.validator

import edu.austral.ingsis.gradle.common.token.Token

interface Validator {
    fun validate(
        tokens: List<Token>
    ): Boolean
}

interface CompositeValidator : Validator {
    val validators: List<Validator>
}
