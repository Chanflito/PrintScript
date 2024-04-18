package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val output_001 = NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1)))

val output_002 =
    SumNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_003 =
    SumNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        StringLiteral("hola", TokenPosition(Position(1, 1), Position(1, 1))),
        StringLiteral("loco", TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_004 =
    MultiplyNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_005 =
    DivideNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_006 =
    DeclarationNode(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 7), Position(1, 8)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
    )

val output_007 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 16), Position(1, 17)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 18), Position(1, 19))),
    )

val output_008 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 16), Position(1, 17)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
        NumberLiteralNode(
            5.0,
            TokenPosition(Position(1, 18), Position(1, 19)),
        ),
    )

//
val output_009 =
    PrintLnNode(
        TokenPosition(Position(1, 1), Position(1, 8)),
        SumNode(
            TokenPosition(Position(1, 12), Position(1, 12)),
            NumberLiteralNode(5.0, TokenPosition(Position(1, 10), Position(1, 11))),
            NumberLiteralNode(7.0, TokenPosition(Position(1, 12), Position(1, 13))),
        ),
    )

//
val output_010 =
    MultiplyNode(
        TokenPosition(Position(1, 6), Position(1, 7)),
        SumNode(
            TokenPosition(Position(1, 3), Position(1, 4)),
            NumberLiteralNode(5.0, TokenPosition(Position(1, 2), Position(1, 3))),
            NumberLiteralNode(7.0, TokenPosition(Position(1, 4), Position(1, 5))),
        ),
        NumberLiteralNode(
            4.0,
            TokenPosition(Position(1, 7), Position(1, 8)),
        ),
    )

//
val output_011 =
    ReadInputNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        StringLiteral("hola lucho", TokenPosition(Position(1, 6), Position(1, 7))),
    )

val output_012 =
    ReadInputNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 6), Position(1, 7))),
    )

val output_013 =
    ReadEnvNode(
        TokenPosition(Position(1, 1), Position(1, 4)),
        "LUCHO_ENV",
    )

val output_014 =
    IfStatement(
        TokenPosition(Position(1, 1), Position(1, 4)),
        IdentifierNode("a", TokenPosition(Position(1, 6), Position(1, 7))),
        BlockNode(
            TokenPosition(Position(2, 1), Position(2, 4)),
            listOf(
                PrintLnNode(
                    TokenPosition(Position(2, 1), Position(2, 4)),
                    StringLiteral("a is true", TokenPosition(Position(2, 6), Position(2, 7))),
                ),
            ),
        ),
    )

val output_015 =
    IfElseStatement(
        TokenPosition(Position(1, 1), Position(1, 4)),
        IdentifierNode("a", TokenPosition(Position(1, 6), Position(1, 7))),
        BlockNode(
            TokenPosition(Position(2, 1), Position(2, 4)),
            listOf(
                PrintLnNode(
                    TokenPosition(Position(2, 1), Position(2, 4)),
                    StringLiteral("a is true", TokenPosition(Position(2, 6), Position(2, 7))),
                ),
            ),
        ),
        BlockNode(
            TokenPosition(Position(4, 1), Position(4, 4)),
            listOf(
                PrintLnNode(
                    TokenPosition(Position(4, 1), Position(4, 4)),
                    StringLiteral("a is false", TokenPosition(Position(4, 6), Position(4, 7))),
                ),
            ),
        ),
    )
