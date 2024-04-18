package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Colon
import edu.austral.ingsis.gradle.common.token.Divide
import edu.austral.ingsis.gradle.common.token.ElseKeyword
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.IfKeyword
import edu.austral.ingsis.gradle.common.token.LeftBrace
import edu.austral.ingsis.gradle.common.token.LeftParenthesis
import edu.austral.ingsis.gradle.common.token.LetKeyword
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
// if,IfKeyword,(2,1),(2,3)
// (,LeftParenthesis,(2,4),(2,5)
// a,Identifier,(2,5),(2,6)
// ),RightParenthesis,(2,6),(2,7)
// {,LeftBrace,(2,8),(2,9)
// println,PrintlnKeyword,(3,6),(3,13)
// (,LeftParenthesis,(3,13),(3,14)
// "a is true",StringValue,(3,14),(3,25)
// ),RightParenthesis,(3,25),(3,26)
// ;,SemiColon,(3,26),(3,27)
// },RightBrace,(4,1),(4,2)
// else,ElseKeyword,(4,3),(4,7)
// {,LeftBrace,(4,8),(4,9)
// println,PrintlnKeyword,(5,6),(5,13)
// (,LeftParenthesis,(5,13),(5,14)
// "a is false",StringValue,(5,14),(5,26)
// ),RightParenthesis,(5,26),(5,27)
// ;,SemiColon,(5,27),(5,28)
// },RightBrace,(6,1),(6,2)
