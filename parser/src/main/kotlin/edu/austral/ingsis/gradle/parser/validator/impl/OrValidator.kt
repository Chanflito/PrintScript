package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.validator.SyntaxValidator

class OrValidator(
    private val validators: List<SyntaxValidator>,
) : SyntaxValidator {
    override fun validate(
        tokens: List<Token>,
        index: Int,
    ): Boolean {
        return validators.any { it.validate(tokens, index) }
    }
}
