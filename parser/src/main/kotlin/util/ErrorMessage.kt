package util

import common.token.Token

interface ErrorMessage{
    override fun toString() : String
}


data class NoTokenFoundErrorMessage (val index: Int) : ErrorMessage{
    override fun toString() : String {
        return "No token found at index $index of the token's list."
    }
}

data class ExpectedTokenErrorMessage (val expectedToken: String, val token: Token) : ErrorMessage{
    override fun toString() : String {
        return "Expected token $expectedToken but found ${token.tokenType} between ${token.startPosition} and ${token.endPosition}"
    }
}

data class InvalidTokenErrorMessage (val token: Token) : ErrorMessage{
    override fun toString() : String {
        return "Invalid token ${token.tokenType} between ${token.startPosition} and ${token.endPosition}"
    }
}