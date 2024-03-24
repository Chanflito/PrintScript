package validator.impl

import common.token.Token
import util.currentToken
import util.isSemiColon
import validator.SyntaxValidator

class SemiColonValidator : SyntaxValidator{
    override fun validate(tokens: List<Token>, index: Int): Boolean {
        return isSemiColon(currentToken(tokens,index))
    }
}