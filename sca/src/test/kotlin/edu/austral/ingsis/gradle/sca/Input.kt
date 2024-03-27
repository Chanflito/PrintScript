package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.*
import edu.austral.ingsis.gradle.common.token.*

val input_001=listOf(
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

val input_002= listOf(
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