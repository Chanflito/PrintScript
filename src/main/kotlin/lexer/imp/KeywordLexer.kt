package lexer.imp

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.createToken
import lexer.util.isInQuotes

class KeywordLexer(
    private val tokens: Map<String, TokenType>,
) : Lexer {
    private val regex = Regex("""\b(${tokens.keys.joinToString("|")})\b""")

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { result ->
            if (isInQuotes(result, code)) null
            else createToken(result, code, tokens[result.value] ?: TokenType.UNKNOWN)
        }.toList()
    }
}
