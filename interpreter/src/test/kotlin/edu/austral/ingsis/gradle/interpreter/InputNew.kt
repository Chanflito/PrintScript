package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.ConstKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val input_001n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                StringNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                StringLiteral(
                    "hola",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
        ),
    )

val input_002n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                StringNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                StringLiteral(
                    "hola",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            ReAssignationNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                SumNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                    StringLiteral(
                        "mundo",
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                ),
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_003n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                NumberNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                NumberLiteralNode(
                    1,
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            ReAssignationNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                SumNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                    NumberLiteralNode(
                        2,
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                ),
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_004n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            ReAssignationNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                SumNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                    NumberLiteralNode(
                        2,
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                ),
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_005n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                NumberNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                NumberLiteralNode(
                    1,
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                NumberNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                NumberLiteralNode(
                    1,
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
        ),
    )

val input_006n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                NumberNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                NumberLiteralNode(
                    1,
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode(
                    "x",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
        ),
    )

val input_007n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                StringNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                StringLiteral(
                    "hola",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            ReAssignationNode(
                TokenPosition(Position(0, 0), Position(0, 20)),
                StringLiteral("mundo", TokenPosition(Position(0, 0), Position(0, 0))),
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )

val input_008n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                BooleanNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                BooleanLiteralNode(
                    true,
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            IfStatement(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                BlockNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    listOf(
                        DeclarationAssignation(
                            LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                            TokenPosition(Position(0, 0), Position(0, 20)),
                            NumberNodeType,
                            IdentifierNode("y", TokenPosition(Position(0, 0), Position(0, 0))),
                            NumberLiteralNode(
                                1,
                                TokenPosition(Position(0, 0), Position(0, 0)),
                            ),
                        ),
                    ),
                ),
            ),
        ),
    )

val input_009n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 20)),
                BooleanNodeType,
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                BooleanLiteralNode(
                    false,
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            IfElseStatement(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode("x", TokenPosition(Position(0, 0), Position(0, 0))),
                BlockNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    listOf(
                        DeclarationAssignation(
                            LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                            TokenPosition(Position(0, 0), Position(0, 20)),
                            NumberNodeType,
                            IdentifierNode("y", TokenPosition(Position(0, 0), Position(0, 0))),
                            NumberLiteralNode(
                                1,
                                TokenPosition(Position(0, 0), Position(0, 0)),
                            ),
                        ),
                    ),
                ),
                BlockNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    listOf(
                        DeclarationAssignation(
                            LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                            TokenPosition(Position(0, 0), Position(0, 20)),
                            NumberNodeType,
                            IdentifierNode("z", TokenPosition(Position(0, 0), Position(0, 0))),
                            NumberLiteralNode(
                                1,
                                TokenPosition(Position(0, 0), Position(0, 0)),
                            ),
                        ),
                    ),
                ),
            ),
        ),
    )

val input_010n =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 3))),
                TokenPosition(Position(0, 0), Position(0, 24)),
                StringNodeType,
                IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                ReadEnvNode(TokenPosition(Position(0, 0), Position(0, 0)), "JAVA_HOME"),
            ),
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
            ),
        ),
    )
