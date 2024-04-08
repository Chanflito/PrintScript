package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IntLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberType
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.StringType
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val input_001n =
    ProgramNode(
        tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
        children =
            listOf(
                DeclarationNode(
                    keyword =
                        LetKeywordNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    type =
                        StringType(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    identifierNode =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                ),
                ReAssignationNode(
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    identifierNode =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                    expression =
                        StringLiteralNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            type =
                                StringType(
                                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                                ),
                            value = "Hello World",
                        ),
                ),
                PrintLnNode(
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    expression =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                ),
            ),
    )

val input_002n =
    ProgramNode(
        tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
        children =
            listOf(
                DeclarationNode(
                    keyword =
                        LetKeywordNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    type =
                        StringType(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    identifierNode =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                ),
                ReAssignationNode(
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    identifierNode =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                    expression =
                        IntLiteralNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            type =
                                NumberType(
                                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                                ),
                            value = 5,
                        ),
                ),
                PrintLnNode(
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    expression =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                ),
            ),
    )

val input_003n =
    ProgramNode(
        tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
        children =
            listOf(
                DeclarationNode(
                    keyword =
                        LetKeywordNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    type =
                        StringType(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    identifierNode =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                ),
                ReAssignationNode(
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    identifierNode =
                        IdentifierNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            name = "a",
                        ),
                    expression =
                        StringLiteralNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            type =
                                StringType(
                                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                                ),
                            value = "Hello World",
                        ),
                ),
                PrintLnNode(
                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                    expression =
                        SumNode(
                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                            left =
                                IdentifierNode(
                                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                                    name = "a",
                                ),
                            right =
                                IntLiteralNode(
                                    tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                                    type =
                                        NumberType(
                                            tokenPosition = TokenPosition(Position(0, 0), Position(0, 0)),
                                        ),
                                    value = 5,
                                ),
                        ),
                ),
            ),
    )
