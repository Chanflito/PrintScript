package edu.austral.ingsis.gradle.parser.validator.impl

import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.validator.SyntaxValidator

class TypeAssignmentValidator(
    private val validators: List<SyntaxValidator> =
        listOf(
            OrValidator(
                listOf(
                    ConstValidator(),
                    LetValidator(),
                ),
            ),
            IdentifierValidator(),
            ColonValidator(),
            TypeValidator(),
            SemiColonValidator(),
        ),
) : SyntaxValidator {
    override fun validate(
        tokens: List<Token>,
        index: Int,
    ): Boolean {
        var currentIndex = index
        return validators.all { validator ->
            val isValid = validator.validate(tokens, currentIndex)
            currentIndex++
            isValid
        }
    }
}
