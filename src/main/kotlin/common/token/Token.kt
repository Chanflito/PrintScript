package common.token

data class Token(val value:String,
                 val tokenType: TokenType,
                 val startPosition: Position,
                 val endPosition: Position)
