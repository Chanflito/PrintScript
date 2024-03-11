package lexer.imp

import common.token.Token
import common.token.TokenType
import lexer.Lexer

class OperatorLexer ():Lexer{
    private val tokens: Map<String, TokenType> = mapOf(
        "+" to TokenType.PLUS,
        "-" to TokenType.MINUS,
        "*" to TokenType.MULTIPLY,
        "/" to TokenType.DIVIDE,
        "=" to TokenType.DIVIDE,
        "(" to TokenType.LEFT_PARENTHESIS,
        ")" to TokenType.RIGHT_PARENTHESIS,
        ":" to TokenType.COLON,
        ";" to TokenType.SEMI_COLON
    );
    private val regex= Regex("""(\\+|-|\\*|/|=|\\(|\\)|:|;)""")
    override fun splitIntoTokens(code: String): List<Token> {
        TODO("Not yet implemented")
    }
}