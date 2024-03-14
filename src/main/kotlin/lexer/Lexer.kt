package lexer

import common.token.Token

interface Lexer {
    fun splitIntoTokens(code:String): List<Token>;
}