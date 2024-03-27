package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TypeString
import edu.austral.ingsis.gradle.common.token.ValueString

val input_001 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, Position(1, 16), Position(1, 17)),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "aBlue",
                    Token("a", Identifier, Position(1, 5), Position(1, 10)),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "string",
                            Token("string", TypeString, Position(1, 12), Position(1, 18)),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "blue",
                    Token("blue", ValueString, Position(1, 18), Position(1, 19)),
                    StringNode,
                    emptyList(),
                ),
            ),
        ),
    )

val input_002 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, Position(1, 16), Position(1, 17)),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a_Blue",
                    Token("a", Identifier, Position(1, 5), Position(1, 10)),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "string",
                            Token("string", TypeString, Position(1, 12), Position(1, 18)),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "blue",
                    Token("blue", ValueString, Position(1, 18), Position(1, 19)),
                    StringNode,
                    emptyList(),
                ),
            ),
        ),
    )

val input_003 =
    listOf(
        ASTNodeImpl(
            "println",
            Token("println", PrintlnKeyword, Position(3, 1), Position(3, 8)),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "b",
                    Token("b", Identifier, Position(3, 12), Position(3, 13)),
                    IdentifierNode,
                    emptyList(),
                ),
            ),
        ),
    )

val input_004 =
    listOf(
        ASTNodeImpl(
            "println",
            Token("println", PrintlnKeyword, Position(3, 1), Position(3, 8)),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "+",
                    Token("+", Plus, Position(3, 11), Position(3, 12)),
                    OperatorNode,
                    listOf(
                        ASTNodeImpl(
                            "a",
                            Token("a", Identifier, Position(3, 10), Position(3, 11)),
                            IdentifierNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "b",
                            Token("b", Identifier, Position(3, 12), Position(3, 13)),
                            IdentifierNode,
                            emptyList(),
                        ),
                    ),
                ),
            ),
        ),
    )
