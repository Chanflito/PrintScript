package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.IfStatement
import edu.austral.ingsis.gradle.common.ast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val input_001 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("aBlue", TokenPosition(Position(0, 0), Position(0, 0))),
        StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_002 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("a_Blue", TokenPosition(Position(0, 0), Position(0, 0))),
        StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_003 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("a_blue", TokenPosition(Position(0, 0), Position(0, 0))),
        StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_004 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_005 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        SumNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
            IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
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
    DeclarationNode(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("aCamel", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_008 =
    DeclarationNode(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("a_snake", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_009 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        ReadInputNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            StringLiteral("SOME_INPUT", TokenPosition(Position(0, 0), Position(0, 0))),
        ),
    )

val input_010 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        ReadEnvNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            "SOME_ENV",
        ),
    )

val input_011 =

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
    )

val input_012 =
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
    )

val input_013 =
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
    )

val input_014 =
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
    )

val input_015 =
    ReadInputNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        SumNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
            IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0))),
        ),
    )

val input_016 =
    ReadInputNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringLiteral(
            "SOME_INPUT",
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_017 =
    IfStatement(
        TokenPosition(Position(0, 0), Position(0, 0)),
        condition = IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
        ifBlock =
            BlockNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                listOf(
                    ReadInputNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        IdentifierNode(
                            "a",
                            TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    ),
                ),
            ),
    )

val input_018 =
    ProgramNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode(
                    "a_snake",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
                StringLiteral(
                    "some string",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            DeclarationAssignation(
                LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                TokenPosition(Position(0, 0), Position(0, 0)),
                StringNodeType,
                IdentifierNode(
                    "aCamel",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
                StringLiteral(
                    "some string",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
            IfElseStatement(
                TokenPosition(Position(0, 0), Position(0, 0)),
                condition =
                    IdentifierNode(
                        "a_snake",
                        TokenPosition(Position(0, 0), Position(0, 0)),
                    ),
                ifBlock =
                    BlockNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        listOf(
                            ReadInputNode(
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
                            ReadEnvNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                "ENV",
                            ),
                            ReadInputNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                SumNode(
                                    TokenPosition(Position(0, 0), Position(0, 0)),
                                    IdentifierNode(
                                        "a_snake",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                    IdentifierNode(
                                        "aCamel",
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                    ),
                                ),
                            ),
                        ),
                    ),
            ),
        ),
    )
