package validator.impl

import common.token.Token
import util.currentToken
import util.isColon
import validator.SyntaxValidator

class ColonValidator : SyntaxValidator {
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        return isColon(currentToken(tokens, index))
    }
}