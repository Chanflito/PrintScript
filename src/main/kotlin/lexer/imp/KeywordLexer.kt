package lexer.imp

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.createToken

class KeywordLexer(
    private val tokens: Map<String, TokenType>,
) : Lexer {
    private val regex = Regex("""\b(${tokens.keys.joinToString("|")})\b""")

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).map { matchResult ->
            createToken(matchResult, code, tokens[matchResult.value] ?: TokenType.UNKNOWN)
        }.toList()
    }
}
