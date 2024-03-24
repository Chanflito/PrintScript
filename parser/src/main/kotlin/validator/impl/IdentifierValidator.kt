package validator.impl

import common.token.Token
import util.currentToken
import util.isIdentifier
import validator.SyntaxValidator

class IdentifierValidator : SyntaxValidator {
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        return isIdentifier(currentToken(tokens, index))
    }
}