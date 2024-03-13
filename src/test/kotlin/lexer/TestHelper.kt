package lexer

import common.token.Token

fun compareTokens(expected: List<Token>, actual: List<Token>): Boolean {
    if (expected.size != actual.size) return false

    for (i in expected.indices) {
        val expectedToken = expected[i]
        val actualToken = actual[i]

        if (expectedToken.value != actualToken.value) return false
        if (expectedToken.tokenType != actualToken.tokenType) return false
        if (expectedToken.startPosition != actualToken.startPosition) return false
        if (expectedToken.endPosition != actualToken.endPosition) return false
    }

    return true
}
