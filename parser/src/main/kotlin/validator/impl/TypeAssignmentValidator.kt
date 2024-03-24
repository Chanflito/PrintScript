package validator.impl

import common.token.Token
import validator.SyntaxValidator

class TypeAssignmentValidator (private val validators: List<SyntaxValidator> = listOf(
    LetValidator(), IdentifierValidator(), ColonValidator(), TypeValidator(), SemiColonValidator()
)): SyntaxValidator {

    override fun validate(tokens: List<Token>, index: Int): Boolean {
        var currentIndex = index
        return validators.all { validator ->
            val isValid = validator.validate(tokens, currentIndex)
            currentIndex++
            isValid
        }
    }

}