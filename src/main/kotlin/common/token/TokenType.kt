package common.token

enum class TokenType {
    //Keywords
    LET_KEYWORD,
    PRINTLN_KEYWORD,

    IDENTIFIER,

    //Types
    TYPE_NUMBER,
    TYPE_STRING,

    //Values
    VALUE_NUMBER,
    VALUE_STRING,

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

    EOF, //End Of File, should be useful for the parser?
    LF, //Line feed
    UNKNOWN
}