package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.ConstKeyword
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.ElseKeyword
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.IfKeyword
import edu.austral.ingsis.gradle.common.token.LeftBrace
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.Minus
import edu.austral.ingsis.gradle.common.token.Multiply
import edu.austral.ingsis.gradle.common.token.NumberType
import edu.austral.ingsis.gradle.common.token.NumberValue
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.ReadEnvKeyword
import edu.austral.ingsis.gradle.common.token.ReadInputKeyword
import edu.austral.ingsis.gradle.common.token.RightBrace
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
        Token("+", Plus, TokenPosition(Position(1, 12), Position(1, 12))),
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
        Token("readInput", ReadInputKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(1, 5), Position(1, 6))),
        Token("hola lucho", StringValue, TokenPosition(Position(1, 6), Position(1, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 17), Position(1, 18))),
        Token(";", SemiColon, TokenPosition(Position(1, 18), Position(1, 19))),
    )

val input_012 =
    listOf(
        Token("readInput", ReadInputKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(1, 5), Position(1, 6))),
        Token("5", NumberValue, TokenPosition(Position(1, 6), Position(1, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 17), Position(1, 18))),
        Token(";", SemiColon, TokenPosition(Position(1, 18), Position(1, 19))),
    )

val input_013 =
    listOf(
        Token("readEnv", ReadEnvKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(1, 5), Position(1, 6))),
        Token("LUCHO_ENV", StringValue, TokenPosition(Position(1, 6), Position(1, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 17), Position(1, 18))),
        Token(";", SemiColon, TokenPosition(Position(1, 18), Position(1, 19))),
    )

val input_014 =
    listOf(
        Token("if", IfKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(1, 5), Position(1, 6))),
        Token("a", Identifier, TokenPosition(Position(1, 6), Position(1, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 17), Position(1, 18))),
        Token("{", LeftBrace, TokenPosition(Position(1, 18), Position(1, 19))),
        Token("println", PrintlnKeyword, TokenPosition(Position(2, 1), Position(2, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(2, 5), Position(2, 6))),
        Token("a is true", StringValue, TokenPosition(Position(2, 6), Position(2, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(2, 17), Position(2, 18))),
        Token(";", SemiColon, TokenPosition(Position(2, 18), Position(2, 19))),
        Token("}", RightBrace, TokenPosition(Position(3, 1), Position(3, 2))),
    )

val input_015 =
    listOf(
        Token("if", IfKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(1, 5), Position(1, 6))),
        Token("a", Identifier, TokenPosition(Position(1, 6), Position(1, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(1, 17), Position(1, 18))),
        Token("{", LeftBrace, TokenPosition(Position(1, 18), Position(1, 19))),
        Token("println", PrintlnKeyword, TokenPosition(Position(2, 1), Position(2, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(2, 5), Position(2, 6))),
        Token("a is true", StringValue, TokenPosition(Position(2, 6), Position(2, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(2, 17), Position(2, 18))),
        Token(";", SemiColon, TokenPosition(Position(2, 18), Position(2, 19))),
        Token("}", RightBrace, TokenPosition(Position(3, 1), Position(3, 2))),
        Token("else", ElseKeyword, TokenPosition(Position(3, 3), Position(3, 4))),
        Token("{", LeftBrace, TokenPosition(Position(3, 5), Position(3, 6))),
        Token("println", PrintlnKeyword, TokenPosition(Position(4, 1), Position(4, 4))),
        Token("(", LeftParenthesis, TokenPosition(Position(4, 5), Position(4, 6))),
        Token("a is false", StringValue, TokenPosition(Position(4, 6), Position(4, 7))),
        Token(")", RightParenthesis, TokenPosition(Position(4, 17), Position(4, 18))),
        Token(";", SemiColon, TokenPosition(Position(4, 18), Position(4, 19))),
        Token("}", RightBrace, TokenPosition(Position(5, 1), Position(5, 2))),
    )

val input_016 =
    listOf(
        Token("readEnv", ReadEnvKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("(", LeftParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("LUCHO_ENV", StringValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(")", RightParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("if", IfKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("(", LeftParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("a", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(")", RightParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("{", LeftBrace, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("println", PrintlnKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("(", LeftParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("a is true", StringValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(")", RightParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("}", RightBrace, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("else", ElseKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("{", LeftBrace, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("println", PrintlnKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("(", LeftParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("a is false", StringValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(")", RightParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("}", RightBrace, TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_017 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("a", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(":", Colon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("number", NumberType, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("=", Assignation, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("5", NumberValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("let", LetKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("b", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(":", Colon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("number", NumberType, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("=", Assignation, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("6", NumberValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("println", PrintlnKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("(", LeftParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("a", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("+", Plus, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("b", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(")", RightParenthesis, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
    )

// const b: string = "this should be valid in 1.1";
val input_018 =
    listOf(
        Token("const", ConstKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("b", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(":", Colon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("string", StringType, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("=", Assignation, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("\"this should be valid in 1.1\"", StringValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
    )

// let cuenta: number = 5*5-8/4+2;
val input_019 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("cuenta", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(":", Colon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("number", NumberType, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("=", Assignation, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("5", NumberValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("*", Multiply, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("5", NumberValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("-", Minus, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("8", NumberValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("/", Divide, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("4", NumberValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("+", Plus, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("2", NumberValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(";", SemiColon, TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_020 =
    listOf(
        Token("let", LetKeyword, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("cuenta", Identifier, TokenPosition(Position(0, 0), Position(0, 0))),
        Token(":", Colon, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("string", StringType, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("=", Assignation, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("hola", StringValue, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("*", Multiply, TokenPosition(Position(0, 0), Position(0, 0))),
        Token("loco", StringValue, TokenPosition(Position(0, 0), Position(0, 0))),
    )
