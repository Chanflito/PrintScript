package lexer.imp

import common.token.Token
import common.token.TokenType
import lexer.Lexer
import lexer.util.createToken

class IdentifierLexer (private val constraints:List<String>):Lexer{
    private val regex= Regex("""[a-zA-Z_]\w*""")
    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull{ matchResult ->
            if (!constraints.contains(matchResult.value)){
                createToken(matchResult,code,TokenType.IDENTIFIER)
            }else null
        }.toList();
    }
}