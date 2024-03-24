package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.token.*

val input_001=listOf(Token("5", ValueNumber, Position(1, 1), Position(1, 1)))

val input_002= listOf(
    Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
    Token("+", Plus, Position(1, 1), Position(1, 1)),
    Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
)

val input_003= listOf(
    Token("hola", ValueString, Position(1, 1), Position(1, 1)),
    Token("+", Plus, Position(1, 1), Position(1, 1)),
    Token("loco", ValueString, Position(1, 1), Position(1, 1))
)

val input_004= listOf(
    Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
    Token("*", Multiply, Position(1, 1), Position(1, 1)),
    Token("5", ValueNumber, Position(1, 1), Position(1, 1))
)

val input_005= listOf(
    Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
    Token("/", Divide, Position(1, 1), Position(1, 1)),
    Token("5", ValueNumber, Position(1, 1), Position(1, 1))
)

val input_006= listOf(
    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
    Token("a", Identifier, Position(1, 5), Position(1, 6)),
    Token(":", Colon, Position(1, 7), Position(1, 8)),
    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
    Token(";", SemiColon, Position(1, 19), Position(1, 20)),
)

val input_007=listOf(
    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
    Token("a", Identifier, Position(1, 5), Position(1, 6)),
    Token(":", Colon, Position(1, 7), Position(1, 8)),
    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
    Token("=", Assignation, Position(1, 16), Position(1, 17)),
    Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
    Token(";", SemiColon, Position(1, 19), Position(1, 20))
);

val input_008=listOf(
    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
    Token("a", Identifier, Position(1, 5), Position(1, 6)),
    Token(":", Colon, Position(1, 7), Position(1, 8)),
    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
    Token("=", Assignation, Position(1, 16), Position(1, 17)),
    Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
    Token(";", SemiColon, Position(1, 19), Position(1, 20)),
    Token("5", ValueNumber, Position(2, 1), Position(2, 2)),
    Token("+", Plus, Position(2, 3), Position(2, 4)),
    Token("5", ValueNumber, Position(2, 5), Position(2, 6)),
    Token(";", SemiColon, Position(2, 7), Position(2, 8))
);

val input_009=listOf(
    Token("println", PrintlnKeyword, Position(1,1), Position(1,8)),
    Token("(", LeftParenthesis, Position(1,9), Position(1,10)),
    Token("5", ValueNumber, Position(1,10), Position(1,11)),
    Token("+", Plus, Position(1,11), Position(1,12)),
    Token("7", ValueNumber, Position(1,12), Position(1,13)),
    Token(")", RightParenthesis, Position(1,13), Position(1,14))
)

val input_010=listOf(
    Token("(", LeftParenthesis, Position(1,1), Position(1,2)),
    Token("5", ValueNumber, Position(1,2), Position(1,3)),
    Token("+", Plus, Position(1,3), Position(1,4)),
    Token("7", ValueNumber, Position(1,4), Position(1,5)),
    Token(")", RightParenthesis, Position(1,5), Position(1,6)),
    Token("*", Multiply, Position(1,6), Position(1,7)),
    Token("4", ValueNumber, Position(1,7), Position(1,8))
)


val input_011= listOf(
    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
    Token("a", Identifier, Position(1, 5), Position(1, 6)),
    Token(":", Colon, Position(1, 6), Position(1, 7)),
    Token("number", TypeNumber, Position(1, 8), Position(1, 14)),
    Token("=", Assignation, Position(1, 15), Position(1, 16)),
    Token("5", ValueNumber, Position(1, 17), Position(1, 18)),
    Token(";", SemiColon, Position(1, 18), Position(1, 19)),
    Token("let", LetKeyword, Position(2, 1), Position(2, 4)),
    Token("b", Identifier, Position(2, 5), Position(2, 6)),
    Token(":", Colon, Position(2, 6), Position(2, 7)),
    Token("number", TypeNumber, Position(2, 8), Position(2, 14)),
    Token("=", Assignation, Position(2, 15), Position(2, 16)),
    Token("5", ValueNumber, Position(2, 17), Position(2, 18)),
    Token(";", SemiColon, Position(2, 18), Position(2, 19)),
    Token("println", PrintlnKeyword, Position(3, 1), Position(3, 8)),
    Token("(", LeftParenthesis, Position(3, 9), Position(3, 10)),
    Token("a", Identifier, Position(3, 10), Position(3, 11)),
    Token("+", Plus, Position(3, 11), Position(3, 12)),
    Token("b", Identifier, Position(3, 12), Position(3, 13)),
    Token(")", RightParenthesis, Position(3, 13), Position(3, 14)),
    Token(";", SemiColon, Position(3, 14), Position(3, 15)
    )
)

val input_012 = listOf(
    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
    Token("a", Identifier, Position(1, 5), Position(1, 6)),
    Token(":", Colon, Position(1, 6), Position(1, 7)),
    Token("string", TypeString, Position(1, 8), Position(1, 14)),
    Token("=", Assignation, Position(1, 15), Position(1, 16)),
    Token("hola", ValueString, Position(1, 17), Position(1, 18)),
    Token(";", SemiColon, Position(1, 18), Position(1, 19)),
    Token("let", LetKeyword, Position(2, 1), Position(2, 4)),
    Token("b", Identifier, Position(2, 5), Position(2, 6)),
    Token(":", Colon, Position(2, 6), Position(2, 7)),
    Token("string", TypeString, Position(2, 8), Position(2, 14)),
    Token("=", Assignation, Position(2, 15), Position(2, 16)),
    Token("loco", ValueString, Position(2, 17), Position(2, 18)),
    Token(";", SemiColon, Position(2, 18), Position(2, 19)),
    Token("println", PrintlnKeyword, Position(3, 1), Position(3, 8)),
    Token("(", LeftParenthesis, Position(3, 9), Position(3, 10)),
    Token("a", Identifier, Position(3, 10), Position(3, 11)),
    Token("+", Plus, Position(3, 11), Position(3, 12)),
    Token("b", Identifier, Position(3, 12), Position(3, 13)),
    Token(")", RightParenthesis, Position(3, 13), Position(3, 14)),
    Token(";", SemiColon, Position(3, 14), Position(3, 15)
    )
)

val input_013 = listOf(
    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
    Token("a", Identifier, Position(1, 5), Position(1, 6)),
    Token(":", Colon, Position(1, 6), Position(1, 7)),
    Token("string", TypeString, Position(1, 8), Position(1, 14)),
    Token("=", Assignation, Position(1, 15), Position(1, 16)),
    Token("hola", ValueString, Position(1, 17), Position(1, 18)),
    Token(";", SemiColon, Position(1, 18), Position(1, 19)),
    Token("a", Identifier, Position(2, 5), Position(2, 6)),
    Token("=", Assignation, Position(2, 15), Position(2, 16)),
    Token("loco", ValueString, Position(2, 17), Position(2, 18)),
    Token(";", SemiColon, Position(2, 18), Position(2, 19)),
    Token("println", PrintlnKeyword, Position(3, 1), Position(3, 8)),
    Token("(", LeftParenthesis, Position(3, 9), Position(3, 10)),
    Token("a", Identifier, Position(3, 10), Position(3, 11)),
    Token(")", RightParenthesis, Position(3, 13), Position(3, 14)),
    Token(";", SemiColon, Position(3, 14), Position(3, 15)
    )
)