package validator.impl

import common.token.Token
import validator.SyntaxValidator

class VariableDeclarationValidator(
    private val validators: List<SyntaxValidator> = listOf(
        LetValidator(), IdentifierValidator(), ColonValidator(), TypeValidator(), EqualsValidator()
    )
) : SyntaxValidator {
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        var currentIndex = index
        return validators.all { validator ->
            val isValid = validator.validate(tokens, currentIndex)
            currentIndex++
            isValid
        }
    }
}
