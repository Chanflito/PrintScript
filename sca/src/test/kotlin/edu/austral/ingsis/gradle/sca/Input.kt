package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val input_001 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode("aBlue", TokenPosition(Position(0, 0), Position(0, 0))),
                StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_002 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode("a_Blue", TokenPosition(Position(0, 0), Position(0, 0))),
                StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_003 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode("a_blue", TokenPosition(Position(0, 0), Position(0, 0))),
                StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_004 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_005 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                SumNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                    IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
                ),
            ),
        ),
    )

val input_006 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode("a_snake", TokenPosition(Position(0, 0), Position(0, 0))),
                StringLiteral("snake", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode("a_snake", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_007 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationNode(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode("aCamel", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_008 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationNode(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode("a_snake", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )
