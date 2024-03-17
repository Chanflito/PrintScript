package parser

import common.ast.*
import common.token.*

val output_001= ASTNodeImpl(
    5,
    Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
    ValueNode,
    null
)

val output_002 = ASTNodeImpl(
    "+",
    Token("+", Plus, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            ValueNode,
            null
        ),
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            ValueNode,
            null
        )
    )
)

val output_003=ASTNodeImpl(
    "+",
    Token("+", Plus, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            "hola",
            Token("hola", ValueString, Position(1, 1), Position(1, 1)),
            StringNode,
            null
        ),
        ASTNodeImpl(
            "loco",
            Token("loco", ValueString, Position(1, 1), Position(1, 1)),
            StringNode,
            null
        )
    )
)

val output_004 = ASTNodeImpl(
    "*",
    Token("*", Multiply, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            ValueNode,
            null
        ),
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            ValueNode,
            null
        )
    )
);

val output_005 = ASTNodeImpl(
    "/",
    Token("/", Divide, Position(1, 1), Position(1, 1)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            ValueNode,
            null
        ),
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 1), Position(1, 1)),
            ValueNode,
            null
        )
    )
);

val output_006 = ASTNodeImpl(
    "a",
    Token("a", Identifier, Position(1, 5), Position(1, 6)),
    IdentifierNode,
    listOf(
        ASTNodeImpl(
            "let",
            Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
            KeywordNode,
            null
        ),
        ASTNodeImpl(
            "number",
            Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
            TypeNode,
            null
        )
    )
);

val output_007 = ASTNodeImpl(
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
                    null
                ),
                ASTNodeImpl(
                    "number",
                    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
                    TypeNode,
                    null
                )
            )
        ),
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
            ValueNode,
            null
        )
    )
);

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
                    null
                ),
                ASTNodeImpl(
                    "number",
                    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
                    TypeNode,
                    null
                )
            )
        ),
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
            ValueNode,
            null
        )
    )
), ASTNodeImpl(
    "+",
    Token("+", Plus, Position(2, 3), Position(2, 4)),
    OperatorNode,
    listOf(
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(2, 1), Position(2, 2)),
            ValueNode,
            null
        ),
        ASTNodeImpl(
            5,
            Token("5", ValueNumber, Position(2, 5), Position(2, 6)),
            ValueNode,
            null
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
                    5,
                    Token("5",ValueNumber,Position(1,10),Position(1,11)),
                    ValueNode,
                    null
                ),
                ASTNodeImpl(
                    7,
                    Token("7",ValueNumber,Position(1,12),Position(1,13)),
                    ValueNode,
                    null
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
                    5,
                    Token("5",ValueNumber,Position(1,2),Position(1,3)),
                    ValueNode,
                    null
                ),
                ASTNodeImpl(
                    7,
                    Token("7",ValueNumber,Position(1,4),Position(1,5)),
                    ValueNode,
                    null
                )
            )
        ),
        ASTNodeImpl(
            4,
            Token("4",ValueNumber,Position(1,7),Position(1,8)),
            ValueNode,
            null
        )
    )
));
