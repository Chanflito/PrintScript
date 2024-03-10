package lexer.resources

import common.token.Position
import common.token.Token
import common.token.TokenType

val output_1 = listOf(Token("let", TokenType.LET_KEYWORD, Position(1, 1), Position(1, 4)),
                    Token("a",TokenType.IDENTIFIER, Position(1,5),Position(1,6)),
                    Token(":",TokenType.COLON,Position(1,6),Position(1,7)),
                    Token("number",TokenType.TYPE_NUMBER, Position(1,8),Position(1,14)),
                    Token("=",TokenType.ASSIGNATION,Position(1,14),Position(1,15)),
                    Token("5",TokenType.TYPE_NUMBER,Position(1,16),Position(1,17)),
                    Token(";",TokenType.SEMI_COLON, Position(1,17),Position(1,18)),
                    Token("\n",TokenType.LF, Position(1,18),Position(1,20)),
                    Token("println",TokenType.PRINTLN_KEYWORD, Position(2,1),Position(2,8)),
                    Token("(",TokenType.LEFT_PARENTHESIS, Position(2,8),Position(2,9)),
                    Token("a",TokenType.IDENTIFIER, Position(2,9),Position(2,10)),
                    Token(")",TokenType.RIGHT_PARENTHESIS,Position(2,10),Position(2,11)),
                    Token(";",TokenType.SEMI_COLON,Position(2,11),Position(2,12))
    )