package edu.austral.ingsis.gradle.parser.util

import edu.austral.ingsis.gradle.common.token.Token

class CustomException(message: String) : Exception(message) {
    override fun toString(): String {
        return message.toString()
    }
}

class NoTokenFoundException() : Exception("Null token found") {
    override fun toString(): String {
        return message.toString()
    }
}

class NoParserFoundException(token: Token) : Exception("No parser found for token ${token.tokenType}") {
    override fun toString(): String {
        return message.toString()
    }
}

class EndOfFileException : Exception("End of file reached, index out of bounds") {
    override fun toString(): String {
        return message.toString()
    }
}

class MissingTokenException(token: Token, tokenType: String) :
    Exception("Missing token $tokenType at position ${getStartPosition(token)}") {
    override fun toString(): String {
        return message.toString()
    }
}

class ExpectedTokenException(expectedToken: String, token: Token) :
    Exception(
        "Expected token $expectedToken but found ${token.tokenType} between ${getStartPosition(token)} and ${
            getEndPosition(
                token,
            )
        }",
    ) {
    override fun toString(): String {
        return message.toString()
    }
}

class InvalidTokenException(token: Token) :
    Exception("Invalid token ${token.tokenType} at position ${getStartPosition(token)}") {
    override fun toString(): String {
        return message.toString()
    }
}

class InvalidOperatorException(token: Token) :
    Exception("Invalid operator ${token.tokenType} at position ${getStartPosition(token)}") {
    override fun toString(): String {
        return message.toString()
    }
}

class InvalidKeywordException(token: Token) :
    Exception("Invalid keyword ${token.tokenType} at position ${getStartPosition(token)}") {
    override fun toString(): String {
        return message.toString()
    }
}

class InvalidTypeException(token: Token) :
    Exception("Invalid type ${token.tokenType} at position ${getStartPosition(token)}") {
    override fun toString(): String {
        return message.toString()
    }
}

fun getStartPosition(token: Token) = token.tokenPosition.startPosition

private fun getEndPosition(token: Token) = token.tokenPosition.endPosition
