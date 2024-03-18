package parser

import common.ast.*
import common.token.*

val output_001= listOf(ASTNodeImpl(
    5.0,
    Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
    NumberNode,
    emptyList()
))

val output_002 = listOf(ASTNodeImpl(
    "+",
    Token("+", Plus, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            NumberNode,
            emptyList()
        ),
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            NumberNode,
            emptyList()
        )
    )
))

val output_003=listOf(ASTNodeImpl(
    "+",
    Token("+", Plus, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            "hola",
            Token("hola", ValueString, Position(1, 1), Position(1, 1)),
            StringNode,
            emptyList()
        ),
        ASTNodeImpl(
            "loco",
            Token("loco", ValueString, Position(1, 1), Position(1, 1)),
            StringNode,
            emptyList()
        )
    )
))

val output_004 = listOf( ASTNodeImpl(
    "*",
    Token("*", Multiply, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            NumberNode,
            emptyList()
        ),
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            NumberNode,
            emptyList()
        )
    )
));

val output_005 = listOf( ASTNodeImpl(
    "/",
    Token("/", Divide, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            NumberNode,
            emptyList()
        ),
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            NumberNode,
            emptyList()
        )
    )
));

val output_006 = listOf( ASTNodeImpl(
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
            Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
            TypeNode,
            emptyList()
        )
    )
));

val output_007 = listOf( ASTNodeImpl(
    "=",
    Token("=", Assignation, Position(1, 16), Position(1, 17)),
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
                    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
                    TypeNode,
                    emptyList()
                )
            )
        ),
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
            NumberNode,
            emptyList()
        )
    )
));

val output_008 = listOf(ASTNodeImpl(
    "=",
    Token("=", Assignation, Position(1, 16), Position(1, 17)),
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
                    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
                    TypeNode,
                    emptyList()
                )
            )
        ),
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
            NumberNode,
            emptyList()
        )
    )
), ASTNodeImpl(
    "+",
    Token("+", Plus, Position(2, 3), Position(2, 4)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(2, 1), Position(2, 2)),
            NumberNode,
            emptyList()
        ),
        ASTNodeImpl(
            5.0,
            Token("5", ValueNumber, Position(2, 5), Position(2, 6)),
            NumberNode,
            emptyList()
        )
    )
));

val output_009 = listOf(ASTNodeImpl(
    "println",
    Token("println",PrintlnKeyword,Position(1,1),Position(1,8)),
    PrintLnNode,
    listOf(
        ASTNodeImpl(
            "+",
            Token("+", Plus,Position(1,11),Position(1,12)),
            OperatorNode,
            listOf(
                ASTNodeImpl(
                    5.0,
                    Token("5",ValueNumber,Position(1,10),Position(1,11)),
                    NumberNode,
                    emptyList()
                ),
                ASTNodeImpl(
                    7.0,
                    Token("7",ValueNumber,Position(1,12),Position(1,13)),
                    NumberNode,
                    emptyList()
                )
            )
        )
    )
));

val output_010 = listOf(ASTNodeImpl(
    "*",
    Token("*",Multiply,Position(1,6),Position(1,7)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            "+",
            Token("+", Plus,Position(1,3),Position(1,4)),
            OperatorNode,
            listOf(
                ASTNodeImpl(
                    5.0,
                    Token("5",ValueNumber,Position(1,2),Position(1,3)),
                    NumberNode,
                    emptyList()
                ),
                ASTNodeImpl(
                    7.0,
                    Token("7",ValueNumber,Position(1,4),Position(1,5)),
                    NumberNode,
                    emptyList()
                )
            )
        ),
        ASTNodeImpl(
            4.0,
            Token("4",ValueNumber,Position(1,7),Position(1,8)),
            NumberNode,
            emptyList()
        )
    )
));

val output_011 = listOf(
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
                        Token("let", LetKeyword, Position(2, 1), Position(2, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "number",
                        Token("number", TypeNumber, Position(2, 8), Position(2, 14)),
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

val output_012 = listOf(
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
                "b",
                Token("b", Identifier, Position(2, 5), Position(2, 6)),
                IdentifierNode,
                listOf(
                    ASTNodeImpl(
                        "let",
                        Token("let", LetKeyword, Position(2, 1), Position(2, 4)),
                        KeywordNode,
                        emptyList()
                    ),
                    ASTNodeImpl(
                        "string",
                        Token("string", TypeString, Position(2, 8), Position(2, 14)),
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


val output_013 = listOf(
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