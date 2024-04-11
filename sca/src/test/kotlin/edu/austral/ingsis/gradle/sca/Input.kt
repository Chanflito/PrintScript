package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
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

val input_009 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                ReadInputNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    StringLiteral("SOME_INPUT", TokenPosition(Position(0, 0), Position(0, 0))),
                ),
            ),
        ),
    )

val input_010 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            PrintLnNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                ReadEnvNode(
                    TokenPosition(Position(0, 0), Position(0, 0)),
                    "SOME_ENV",
                ),
            ),
        ),
    )

val input_011 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            IfElseStatement(
                TokenPosition(Position(0, 0), Position(0, 0)),
                condition = IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                ifBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                SumNode(
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                    IdentifierNode(
                                        "a",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                    IdentifierNode(
                                        "b",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                ),
                            ),
                        ),
                    ),
                elseBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                SubtractNode(
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                    IdentifierNode(
                                        "a",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                    IdentifierNode(
                                        "b",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                ),
                            ),
                        ),
                    ),
            ),
        ),
    )

val input_012 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            IfElseStatement(
                TokenPosition(Position(0, 0), Position(0, 0)),
                condition = IdentifierNode("aCamel", TokenPosition(Position(0, 0), Position(0, 0))),
                ifBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                IdentifierNode(
                                    "aCamel",
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                ),
                            ),
                        ),
                    ),
                elseBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                IdentifierNode(
                                    "aCamel",
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                ),
                            ),
                        ),
                    ),
            ),
        ),
    )

val input_013 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            IfElseStatement(
                TokenPosition(Position(0, 0), Position(0, 0)),
                condition = IdentifierNode("a_snake", TokenPosition(Position(0, 0), Position(0, 0))),
                ifBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                IdentifierNode(
                                    "a_snake",
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                ),
                            ),
                        ),
                    ),
                elseBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                IdentifierNode(
                                    "a_snake",
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                ),
                            ),
                        ),
                    ),
            ),
        ),
    )

val input_014 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            IfElseStatement(
                TokenPosition(Position(0, 0), Position(0, 0)),
                condition = IdentifierNode("aCamel", TokenPosition(Position(0, 0), Position(0, 0))),
                ifBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                SumNode(
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                    IdentifierNode(
                                        "aCamel",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                    IdentifierNode(
                                        "aCamelCase",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                ),
                            ),
                        ),
                    ),
                elseBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            PrintLnNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                SumNode(
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                    IdentifierNode(
                                        "aCamel",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                    IdentifierNode(
                                        "aCamelCase",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                ),
                            ),
                        ),
                    ),
            ),
        ),
    )

// val input_015=
//    ProgramNode(
//        TokenPosition(Position(0, 0), Position(0, 0)),
//        listOf(
//            ReadInputNode(
//                TokenPosition(Position(0, 0), Position(0, 0)),
//                SumNode(
//                    TokenPosition(Position(0, 0), Position(0, 0)),
//                    IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
//                    IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
//                ),
//            ),
//        ),
//    )
