package common.token

interface IToken {
    val value: String
    val tokenType: TokenType
    val startPosition: Position
    val endPosition: Position
}