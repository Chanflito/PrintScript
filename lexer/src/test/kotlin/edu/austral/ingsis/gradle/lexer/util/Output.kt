package edu.austral.ingsis.gradle.lexer.util

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.NumberType
import edu.austral.ingsis.gradle.common.token.NumberValue
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.RightParenthesis
import edu.austral.ingsis.gradle.common.token.SemiColon
import edu.austral.ingsis.gradle.common.token.StringType
import edu.austral.ingsis.gradle.common.token.StringValue
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenPosition

val output_001 = Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4)))

val output_002_doubleQuote = Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4)))

val output_002_singleQuote = Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4)))

val output_003 = Token("n", Identifier, TokenPosition(Position(1, 5), Position(1, 6)))

val output_004 = Token("number", NumberType, TokenPosition(Position(1, 10), Position(1, 16)))

val output_005 =
    setOf(
        Token(":", Colon, TokenPosition(Position(1, 8), Position(1, 9))),
        Token("=", Assignation, TokenPosition(Position(1, 17), Position(1, 18))),
        Token("(", LeftParenthesis, TokenPosition(Position(1, 19), Position(1, 20))),
        Token("+", Plus, TokenPosition(Position(1, 23), Position(1, 24))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 25), Position(1, 26))),
        Token(";", SemiColon, TokenPosition(Position(1, 27), Position(1, 28))),
    )

val output_006 = Token("hola", StringValue, TokenPosition(Position(1, 19), Position(1, 25)))

val output_007 = Token("19", NumberValue, TokenPosition(Position(1, 18), Position(1, 20)))

val output_008 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 7), Position(1, 8))),
        Token("number", NumberType, TokenPosition(Position(1, 9), Position(1, 15))),
        Token("=", Assignation, TokenPosition(Position(1, 16), Position(1, 17))),
        Token("5", NumberValue, TokenPosition(Position(1, 18), Position(1, 19))),
        Token(";", SemiColon, TokenPosition(Position(1, 19), Position(1, 20))),
        Token("println", PrintlnKeyword, TokenPosition(Position(2, 2), Position(2, 9))),
        Token("(", LeftParenthesis, TokenPosition(Position(2, 9), Position(2, 10))),
        Token("a", Identifier, TokenPosition(Position(2, 10), Position(2, 11))),
        Token(")", RightParenthesis, TokenPosition(Position(2, 11), Position(2, 12))),
    )

val output_009 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("variable", Identifier, TokenPosition(Position(1, 5), Position(1, 13))),
        Token(":", Colon, TokenPosition(Position(1, 14), Position(1, 15))),
        Token("string", StringType, TokenPosition(Position(1, 16), Position(1, 22))),
        Token("=", Assignation, TokenPosition(Position(1, 23), Position(1, 24))),
        Token("+", Plus, TokenPosition(Position(1, 25), Position(1, 26))),
        Token("5", NumberValue, TokenPosition(Position(1, 27), Position(1, 28))),
        Token("let", StringValue, TokenPosition(Position(1, 29), Position(1, 34))),
        Token("+", Plus, TokenPosition(Position(1, 35), Position(1, 36))),
        Token("1", NumberValue, TokenPosition(Position(1, 37), Position(1, 38))),
        Token("+", Plus, TokenPosition(Position(1, 39), Position(1, 40))),
        Token("println", StringValue, TokenPosition(Position(1, 41), Position(1, 50))),
        Token("+", Plus, TokenPosition(Position(1, 51), Position(1, 52))),
        Token("aaalet1", StringValue, TokenPosition(Position(1, 53), Position(1, 62))),
        Token(";", SemiColon, TokenPosition(Position(1, 62), Position(1, 63))),
    )

val output_010 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("name", Identifier, TokenPosition(Position(1, 5), Position(1, 9))),
        Token(":", Colon, TokenPosition(Position(1, 10), Position(1, 11))),
        Token("string", StringType, TokenPosition(Position(1, 12), Position(1, 18))),
        Token("=", Assignation, TokenPosition(Position(1, 19), Position(1, 20))),
        Token("Joe", StringValue, TokenPosition(Position(1, 21), Position(1, 26))),
        Token(";", SemiColon, TokenPosition(Position(1, 26), Position(1, 27))),
        Token("let", LetKeyword, TokenPosition(Position(2, 2), Position(2, 5))),
        Token("lastName", Identifier, TokenPosition(Position(2, 6), Position(2, 14))),
        Token(":", Colon, TokenPosition(Position(2, 15), Position(2, 16))),
        Token("string", StringType, TokenPosition(Position(2, 17), Position(2, 23))),
        Token("=", Assignation, TokenPosition(Position(2, 24), Position(2, 25))),
        Token("Doe", StringValue, TokenPosition(Position(2, 26), Position(2, 31))),
        Token(";", SemiColon, TokenPosition(Position(2, 32), Position(2, 33))),
        Token("println", PrintlnKeyword, TokenPosition(Position(3, 2), Position(3, 9))),
        Token("(", LeftParenthesis, TokenPosition(Position(3, 9), Position(3, 10))),
        Token("name", Identifier, TokenPosition(Position(3, 10), Position(3, 14))),
        Token("+", Plus, TokenPosition(Position(3, 15), Position(3, 16))),
        Token(" ", StringValue, TokenPosition(Position(3, 17), Position(3, 20))),
        Token("+", Plus, TokenPosition(Position(3, 21), Position(3, 22))),
        Token("lastName", Identifier, TokenPosition(Position(3, 23), Position(3, 31))),
        Token(")", RightParenthesis, TokenPosition(Position(3, 31), Position(3, 32))),
        Token(
            ";",
            SemiColon,
            TokenPosition(
                Position(3, 32),
                Position(3, 33),
            ),
        ),
    )

val output_011 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 6), Position(1, 7))),
        Token("number", NumberType, TokenPosition(Position(1, 8), Position(1, 14))),
        Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
        Token("12", NumberValue, TokenPosition(Position(1, 17), Position(1, 19))),
        Token(";", SemiColon, TokenPosition(Position(1, 19), Position(1, 20))),
        Token("let", LetKeyword, TokenPosition(Position(2, 1), Position(2, 4))),
        Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
        Token(":", Colon, TokenPosition(Position(2, 6), Position(2, 7))),
        Token("number", NumberType, TokenPosition(Position(2, 8), Position(2, 14))),
        Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
        Token("4", NumberValue, TokenPosition(Position(2, 17), Position(2, 18))),
        Token(";", SemiColon, TokenPosition(Position(2, 18), Position(2, 19))),
        Token("a", Identifier, TokenPosition(Position(3, 1), Position(3, 2))),
        Token("=", Assignation, TokenPosition(Position(3, 3), Position(3, 4))),
        Token("a", Identifier, TokenPosition(Position(3, 5), Position(3, 6))),
        Token("/", Divide, TokenPosition(Position(3, 7), Position(3, 8))),
        Token("b", Identifier, TokenPosition(Position(3, 9), Position(3, 10))),
        Token(
            ";",
            SemiColon,
            TokenPosition(
                Position(3, 10),
                Position(3, 11),
            ),
        ),
    )
