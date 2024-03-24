package validator.impl

import common.token.Token
import util.currentToken
import util.isAssignation
import validator.SyntaxValidator

class EqualsValidator : SyntaxValidator{
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        return isAssignation(currentToken(tokens,index))
    }
}