package validator

import common.token.Token

interface SyntaxValidator {
    fun validate(tokens: List<Token>, index: Int): Boolean
}

