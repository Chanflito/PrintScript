package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.Multiply
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

val input_001 = listOf(Token("5", NumberValue, TokenPosition(Position(1, 1), Position(1, 1))))

val input_002 =
    listOf(
        Token("5", NumberValue, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("+", Plus, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("5", NumberValue, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val input_003 =
    listOf(
        Token("hola", StringValue, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("+", Plus, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("loco", StringValue, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val input_004 =
    listOf(
        Token("5", NumberValue, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("*", Multiply, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("5", NumberValue, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val input_005 =
    listOf(
        Token("5", NumberValue, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("/", Divide, TokenPosition(Position(1, 1), Position(1, 1))),
        Token("5", NumberValue, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val input_006 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 7), Position(1, 8))),
        Token("number", NumberType, TokenPosition(Position(1, 9), Position(1, 15))),
        Token(";", SemiColon, TokenPosition(Position(1, 19), Position(1, 20))),
    )

val input_007 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 7), Position(1, 8))),
        Token("number", NumberType, TokenPosition(Position(1, 9), Position(1, 15))),
        Token("=", Assignation, TokenPosition(Position(1, 16), Position(1, 17))),
        Token("5", NumberValue, TokenPosition(Position(1, 18), Position(1, 19))),
        Token(";", SemiColon, TokenPosition(Position(1, 19), Position(1, 20))),
    )

val input_008 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 7), Position(1, 8))),
        Token("number", NumberType, TokenPosition(Position(1, 9), Position(1, 15))),
        Token("=", Assignation, TokenPosition(Position(1, 16), Position(1, 17))),
        Token("5", NumberValue, TokenPosition(Position(1, 18), Position(1, 19))),
        Token(";", SemiColon, TokenPosition(Position(1, 19), Position(1, 20))),
        Token("5", NumberValue, TokenPosition(Position(2, 1), Position(2, 2))),
        Token("+", Plus, TokenPosition(Position(2, 3), Position(2, 4))),
        Token("5", NumberValue, TokenPosition(Position(2, 5), Position(2, 6))),
        Token(";", SemiColon, TokenPosition(Position(2, 7), Position(2, 8))),
    )

val input_009 =
    listOf(
        Token("println", PrintlnKeyword, TokenPosition(Position(1, 1), Position(1, 8))),
        Token("(", LeftParenthesis, TokenPosition(Position(1, 9), Position(1, 10))),
        Token("5", NumberValue, TokenPosition(Position(1, 10), Position(1, 11))),
        Token("+", Plus, TokenPosition(Position(1, 11), Position(1, 12))),
        Token("7", NumberValue, TokenPosition(Position(1, 12), Position(1, 13))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 13), Position(1, 14))),
    )

val input_010 =
    listOf(
        Token("(", LeftParenthesis, TokenPosition(Position(1, 1), Position(1, 2))),
        Token("5", NumberValue, TokenPosition(Position(1, 2), Position(1, 3))),
        Token("+", Plus, TokenPosition(Position(1, 3), Position(1, 4))),
        Token("7", NumberValue, TokenPosition(Position(1, 4), Position(1, 5))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 5), Position(1, 6))),
        Token("*", Multiply, TokenPosition(Position(1, 6), Position(1, 7))),
        Token("4", NumberValue, TokenPosition(Position(1, 7), Position(1, 8))),
    )

val input_011 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 6), Position(1, 7))),
        Token("number", NumberType, TokenPosition(Position(1, 8), Position(1, 14))),
        Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
        Token("5", NumberValue, TokenPosition(Position(1, 17), Position(1, 18))),
        Token(";", SemiColon, TokenPosition(Position(1, 18), Position(1, 19))),
        Token("let", LetKeyword, TokenPosition(Position(2, 1), Position(2, 4))),
        Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
        Token(":", Colon, TokenPosition(Position(2, 6), Position(2, 7))),
        Token("number", NumberType, TokenPosition(Position(2, 8), Position(2, 14))),
        Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
        Token("5", NumberValue, TokenPosition(Position(2, 17), Position(2, 18))),
        Token(";", SemiColon, TokenPosition(Position(2, 18), Position(2, 19))),
        Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
        Token("(", LeftParenthesis, TokenPosition(Position(3, 9), Position(3, 10))),
        Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
        Token("+", Plus, TokenPosition(Position(3, 11), Position(3, 12))),
        Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
        Token(")", RightParenthesis, TokenPosition(Position(3, 13), Position(3, 14))),
        Token(";", SemiColon, TokenPosition(Position(3, 14), Position(3, 15))),
    )

val input_012 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 6), Position(1, 7))),
        Token("string", StringType, TokenPosition(Position(1, 8), Position(1, 14))),
        Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
        Token("hola", StringValue, TokenPosition(Position(1, 17), Position(1, 18))),
        Token(";", SemiColon, TokenPosition(Position(1, 18), Position(1, 19))),
        Token("let", LetKeyword, TokenPosition(Position(2, 1), Position(2, 4))),
        Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
        Token(":", Colon, TokenPosition(Position(2, 6), Position(2, 7))),
        Token("string", StringType, TokenPosition(Position(2, 8), Position(2, 14))),
        Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
        Token("loco", StringValue, TokenPosition(Position(2, 17), Position(2, 18))),
        Token(";", SemiColon, TokenPosition(Position(2, 18), Position(2, 19))),
        Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
        Token("(", LeftParenthesis, TokenPosition(Position(3, 9), Position(3, 10))),
        Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
        Token("+", Plus, TokenPosition(Position(3, 11), Position(3, 12))),
        Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
        Token(")", RightParenthesis, TokenPosition(Position(3, 13), Position(3, 14))),
        Token(";", SemiColon, TokenPosition(Position(3, 14), Position(3, 15))),
    )

val input_013 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
        Token(":", Colon, TokenPosition(Position(1, 6), Position(1, 7))),
        Token("string", StringType, TokenPosition(Position(1, 8), Position(1, 14))),
        Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
        Token("hola", StringValue, TokenPosition(Position(1, 17), Position(1, 18))),
        Token(";", SemiColon, TokenPosition(Position(1, 18), Position(1, 19))),
        Token("a", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
        Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
        Token("loco", StringValue, TokenPosition(Position(2, 17), Position(2, 18))),
        Token(";", SemiColon, TokenPosition(Position(2, 18), Position(2, 19))),
        Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
        Token("(", LeftParenthesis, TokenPosition(Position(3, 9), Position(3, 10))),
        Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
        Token(")", RightParenthesis, TokenPosition(Position(3, 13), Position(3, 14))),
        Token(";", SemiColon, TokenPosition(Position(3, 14), Position(3, 15))),
    )
