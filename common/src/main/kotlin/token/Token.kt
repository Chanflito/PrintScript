package common.token

data class Token(val value:String,
                 val tokenType: TokenType,
                 val startPosition: Position,
                 val endPosition: Position){
}

interface TokenType


object LetKeyword : TokenType

object PrintlnKeyword : TokenType

object Identifier : TokenType

object TypeNumber : TokenType

object TypeString : TokenType

object ValueNumber : TokenType

object ValueString : TokenType

object Assignation : TokenType

object Colon : TokenType

object SemiColon : TokenType

object LeftParenthesis : TokenType

object RightParenthesis : TokenType

object Plus : TokenType

object Minus : TokenType

object Multiply : TokenType

object Divide : TokenType
