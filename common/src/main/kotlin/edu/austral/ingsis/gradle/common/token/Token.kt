package edu.austral.ingsis.gradle.common.token

data class Token(
    val value: String,
    val tokenType: TokenType,
    val tokenPosition: TokenPosition,
)

interface TokenType

object LetKeyword : TokenType

object ConstKeyword : TokenType

object PrintlnKeyword : TokenType

object ReadInputKeyword : TokenType

object ReadEnvKeyword : TokenType

object IfKeyword : TokenType

object ElseKeyword : TokenType

object Identifier : TokenType

object NumberType : TokenType

object StringType : TokenType

object BooleanType : TokenType

object NumberValue : TokenType

object StringValue : TokenType

object BooleanValue : TokenType

object Assignation : TokenType

object Colon : TokenType

object SemiColon : TokenType

object LeftParenthesis : TokenType

object RightParenthesis : TokenType

object LeftBrace : TokenType

object RightBrace : TokenType

object Plus : TokenType

object Minus : TokenType

object Multiply : TokenType

object Divide : TokenType
