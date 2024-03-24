package validator.impl

import common.token.Token
import util.currentToken
import util.isType
import validator.SyntaxValidator

class TypeValidator : SyntaxValidator{
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        return isType(currentToken(tokens,index))
    }
}