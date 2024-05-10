package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignationNode
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.IfElseStatementNode
import edu.austral.ingsis.gradle.common.ast.IfStatementNode
import edu.austral.ingsis.gradle.common.ast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.StringLiteralNode
import edu.austral.ingsis.gradle.common.ast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.common.token.defaultTokenPosition

val input_001 =
    DeclarationAssignationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("aBlue", defaultTokenPosition()),
        StringLiteralNode("blue", defaultTokenPosition()),
    )

val input_002 =
    DeclarationAssignationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("a_Blue", defaultTokenPosition()),
        StringLiteralNode("blue", defaultTokenPosition()),
    )

val input_003 =
    DeclarationAssignationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("a_blue", defaultTokenPosition()),
        StringLiteralNode("blue", defaultTokenPosition()),
    )

val input_004 =
    PrintLnNode(
        defaultTokenPosition(),
        IdentifierNode("b", defaultTokenPosition()),
    )

val input_005 =
    PrintLnNode(
        defaultTokenPosition(),
        SumNode(
            defaultTokenPosition(),
            IdentifierNode("a", defaultTokenPosition()),
            IdentifierNode("b", defaultTokenPosition()),
        ),
    )

val input_006 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("a_snake", defaultTokenPosition()),
                StringLiteralNode("snake", defaultTokenPosition()),
            ),
            PrintLnNode(
                defaultTokenPosition(),
                IdentifierNode("a_snake", defaultTokenPosition()),
            ),
        ),
    )

val input_007 =
    DeclarationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("aCamel", defaultTokenPosition()),
    )

val input_008 =
    DeclarationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("a_snake", defaultTokenPosition()),
    )

val input_009 =
    PrintLnNode(
        defaultTokenPosition(),
        ReadInputNode(
            defaultTokenPosition(),
            StringLiteralNode("SOME_INPUT", defaultTokenPosition()),
        ),
    )

val input_010 =
    PrintLnNode(
        defaultTokenPosition(),
        ReadEnvNode(
            defaultTokenPosition(),
            "SOME_ENV",
        ),
    )

val input_011 =

    IfElseStatementNode(
        defaultTokenPosition(),
        condition = IdentifierNode("a", defaultTokenPosition()),
        ifBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        SumNode(
                            defaultTokenPosition(),
                            IdentifierNode(
                                "a",
                                defaultTokenPosition(),
                            ),
                            IdentifierNode(
                                "b",
                                defaultTokenPosition(),
                            ),
                        ),
                    ),
                ),
            ),
        elseBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        SubtractNode(
                            defaultTokenPosition(),
                            IdentifierNode(
                                "a",
                                defaultTokenPosition(),
                            ),
                            IdentifierNode(
                                "b",
                                defaultTokenPosition(),
                            ),
                        ),
                    ),
                ),
            ),
    )

val input_012 =
    IfElseStatementNode(
        defaultTokenPosition(),
        condition = IdentifierNode("aCamel", defaultTokenPosition()),
        ifBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        IdentifierNode(
                            "aCamel",
                            defaultTokenPosition(),
                        ),
                    ),
                ),
            ),
        elseBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        IdentifierNode(
                            "aCamel",
                            defaultTokenPosition(),
                        ),
                    ),
                ),
            ),
    )

val input_013 =
    IfElseStatementNode(
        defaultTokenPosition(),
        condition = IdentifierNode("a_snake", defaultTokenPosition()),
        ifBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        IdentifierNode(
                            "a_snake",
                            defaultTokenPosition(),
                        ),
                    ),
                ),
            ),
        elseBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        IdentifierNode(
                            "a_snake",
                            defaultTokenPosition(),
                        ),
                    ),
                ),
            ),
    )

val input_014 =
    IfElseStatementNode(
        defaultTokenPosition(),
        condition = IdentifierNode("aCamel", defaultTokenPosition()),
        ifBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        SumNode(
                            defaultTokenPosition(),
                            IdentifierNode(
                                "aCamel",
                                defaultTokenPosition(),
                            ),
                            IdentifierNode(
                                "aCamelCase",
                                defaultTokenPosition(),
                            ),
                        ),
                    ),
                ),
            ),
        elseBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    PrintLnNode(
                        defaultTokenPosition(),
                        SumNode(
                            defaultTokenPosition(),
                            IdentifierNode(
                                "aCamel",
                                defaultTokenPosition(),
                            ),
                            IdentifierNode(
                                "aCamelCase",
                                defaultTokenPosition(),
                            ),
                        ),
                    ),
                ),
            ),
    )

val input_015 =
    ReadInputNode(
        defaultTokenPosition(),
        SumNode(
            defaultTokenPosition(),
            IdentifierNode("a", defaultTokenPosition()),
            IdentifierNode("b", defaultTokenPosition()),
        ),
    )

val input_016 =
    ReadInputNode(
        defaultTokenPosition(),
        StringLiteralNode(
            "SOME_INPUT",
            defaultTokenPosition(),
        ),
    )

val input_017 =
    IfStatementNode(
        defaultTokenPosition(),
        condition = IdentifierNode("a", defaultTokenPosition()),
        ifBlock =
            BlockNode(
                defaultTokenPosition(),
                listOf(
                    ReadInputNode(
                        defaultTokenPosition(),
                        IdentifierNode(
                            "a",
                            defaultTokenPosition(),
                        ),
                    ),
                ),
            ),
    )

val input_018 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode(
                    "a_snake",
                    defaultTokenPosition(),
                ),
                StringLiteralNode(
                    "some string",
                    defaultTokenPosition(),
                ),
            ),
            DeclarationAssignationNode(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode(
                    "aCamel",
                    defaultTokenPosition(),
                ),
                StringLiteralNode(
                    "some string",
                    defaultTokenPosition(),
                ),
            ),
            IfElseStatementNode(
                defaultTokenPosition(),
                condition =
                    IdentifierNode(
                        "a_snake",
                        defaultTokenPosition(),
                    ),
                ifBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            ReadInputNode(
                                defaultTokenPosition(),
                                IdentifierNode(
                                    "a_snake",
                                    defaultTokenPosition(),
                                ),
                            ),
                        ),
                    ),
                elseBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            PrintLnNode(
                                defaultTokenPosition(),
                                IdentifierNode(
                                    "a_snake",
                                    defaultTokenPosition(),
                                ),
                            ),
                            ReadEnvNode(
                                defaultTokenPosition(),
                                "ENV",
                            ),
                            ReadInputNode(
                                defaultTokenPosition(),
                                SumNode(
                                    defaultTokenPosition(),
                                    IdentifierNode(
                                        "a_snake",
                                        defaultTokenPosition(),
                                    ),
                                    IdentifierNode(
                                        "aCamel",
                                        defaultTokenPosition(),
                                    ),
                                ),
                            ),
                        ),
                    ),
            ),
        ),
    )
