package edu.austral.ingsis.gradle.interpreter

import common.ast.ASTNodeImpl
import common.ast.*
import common.token.*
import edu.austral.ingsis.gradle.common.ast.*

val input_001 = listOf(
    ASTNodeImpl(
        "println",
        Token("println", PrintlnKeyword, Position(1,1), Position(1,8)),
        PrintLnNode,
        listOf(
            ASTNodeImpl(
                "+",
                Token("+", Plus, Position(1,11), Position(1,12)),
                OperatorNode,
                listOf(
                    ASTNodeImpl(
                        5.0,
                        Token("5", ValueNumber, Position(1,10), Position(1,11)),
                        NumberNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        7.0,
                        Token("7", ValueNumber, Position(1,12), Position(1,13)),
                        NumberNode,
                        emptyList()
                    )
                )
            )
        )
    )
);


val input_002 = listOf(
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(1, 15), Position(1, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(1, 5), Position(1, 6)),
                IdentifierNode,
                emptyList() // No children for identifier node
            ),
            ASTNodeImpl(
                5.0,
                Token("5", ValueNumber, Position(1, 17), Position(1, 18)),
                NumberNode,
                emptyList()
            )
        )
    ),
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(2, 15), Position(2, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "b",
                Token("b", Identifier, Position(2, 5), Position(2, 6)),
                IdentifierNode,
                emptyList() // No children for identifier node
            ),
            ASTNodeImpl(
                5.0,
                Token("5", ValueNumber, Position(2, 17), Position(2, 18)),
                NumberNode,
                emptyList()
            )
        )
    ),
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
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "b",
                        Token("b", Identifier, Position(3, 12), Position(3, 13)),
                        IdentifierNode,
                        emptyList()
                    )
                )
            )
        )
    )
)

val input_003 = listOf(
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(1, 15), Position(1, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(1, 5), Position(1, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "number",
                        Token("number", TypeNumber, Position(1, 8), Position(1, 14)),
                        TypeNode,
                        emptyList()
                    )
                )
            ),
            ASTNodeImpl(
                5.0,
                Token("5", ValueNumber, Position(1, 17), Position(1, 18)),
                NumberNode,
                emptyList()
            )
        )
    ),
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(2, 15), Position(2, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "b",
                Token("b", Identifier, Position(2, 5), Position(2, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "number",
                        Token("number", TypeNumber, Position(1, 8), Position(1, 14)),
                        TypeNode,
                        emptyList()
                    )
                )
            ),
            ASTNodeImpl(
                5.0,
                Token("5", ValueNumber, Position(2, 17), Position(2, 18)),
                NumberNode,
                emptyList()
            )
        )
    ),
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
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "b",
                        Token("b", Identifier, Position(3, 12), Position(3, 13)),
                        IdentifierNode,
                        emptyList()
                    )
                )
            )
        )
    )
)

val input_004 = listOf(
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(1, 15), Position(1, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(1, 5), Position(1, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "number",
                        Token("number", TypeNumber, Position(1, 8), Position(1, 14)),
                        TypeNode,
                        emptyList()
                    )
                )
            ),
            ASTNodeImpl(
                5.0,
                Token("5", ValueNumber, Position(1, 17), Position(1, 18)),
                NumberNode,
                emptyList()
            )
        )
    ),
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(2, 15), Position(2, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(2, 5), Position(2, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "number",
                        Token("number", TypeNumber, Position(1, 8), Position(1, 14)),
                        TypeNode,
                        emptyList()
                    )
                )
            ),
            ASTNodeImpl(
                5.0,
                Token("5", ValueNumber, Position(2, 17), Position(2, 18)),
                NumberNode,
                emptyList()
            )
        )
    )
)

val input_005 = listOf(
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(1, 15), Position(1, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(1, 5), Position(1, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "string",
                        Token("string", TypeString, Position(1, 8), Position(1, 14)),
                        TypeNode,
                        emptyList()
                    )
                )
            ),
            ASTNodeImpl(
                "hola",
                Token("hola", ValueString, Position(1, 17), Position(1, 18)),
                StringNode,
                emptyList()
            )
        )
    ),
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(2, 15), Position(2, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(2, 5), Position(2, 6)),
                IdentifierNode,
                emptyList()
            ),
            ASTNodeImpl(
                "loco",
                Token("loco", ValueString, Position(2, 17), Position(2, 18)),
                StringNode,
                emptyList()
            )
        )
    ),
    ASTNodeImpl(
        "println",
        Token("println", PrintlnKeyword, Position(3, 1), Position(3, 8)),
        PrintLnNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(3, 10), Position(3, 11)),
                IdentifierNode,
                emptyList()
            )
        )
    )
)

val input_006 = listOf(
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(1, 15), Position(1, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "a",
                Token("a", Identifier, Position(1, 5), Position(1, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "string",
                        Token("string", TypeNumber, Position(1, 8), Position(1, 14)),
                        TypeNode,
                        emptyList()
                    )
                )
            ),
            ASTNodeImpl(
                "hola",
                Token("hola", ValueString, Position(1, 17), Position(1, 18)),
                StringNode,
                emptyList()
            )
        )
    ),
    ASTNodeImpl(
        "=",
        Token("=", Assignation, Position(2, 15), Position(2, 16)),
        AssignationNode,
        listOf(
            ASTNodeImpl(
                "b",
                Token("b", Identifier, Position(2, 5), Position(2, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "string",
                        Token("string", TypeString, Position(1, 8), Position(1, 14)),
                        TypeNode,
                        emptyList()
                    )
                )
            ),
            ASTNodeImpl(
                "loco",
                Token("loco", ValueString, Position(2, 17), Position(2, 18)),
                StringNode,
                emptyList()
            )
        )
    ),
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
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "b",
                        Token("b", Identifier, Position(3, 12), Position(3, 13)),
                        IdentifierNode,
                        emptyList()
                    )
                )
            )
        )
    )
)

