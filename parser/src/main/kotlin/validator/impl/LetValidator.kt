package validator.impl

import common.token.Token
import util.currentToken
import util.isLetKeyword
import validator.SyntaxValidator

class LetValidator : SyntaxValidator {
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        return isLetKeyword(currentToken(tokens, index))
    }
}