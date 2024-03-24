package edu.austral.ingsis.gradle.lexer

import edu.austral.ingsis.gradle.common.token.Token

interface Lexer {
    fun splitIntoTokens(code:String): List<Token>
}