package common.token

enum class TokenType {
    //Keyword
    LET_KEYWORD,
    PRINTLN_KEYWORD,

    IDENTIFIER,

    //TYPES
    TYPE_NUMBER,
    TYPE_STRING,


    //Operators
    ASSIGNATION,
    COLON,
    SEMI_COLON,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,

    EOF, //End Of File
    LF, //Line feed
    UNKNOWN
}