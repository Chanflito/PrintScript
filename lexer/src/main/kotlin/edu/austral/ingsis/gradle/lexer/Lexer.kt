package lexer.edu.austral.ingsis.gradle.lexer

import common.token.Token

interface Lexer {
    fun splitIntoTokens(code:String): List<Token>;
}