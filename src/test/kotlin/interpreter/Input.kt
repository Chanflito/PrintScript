package interpreter
import common.ast.ASTNodeImpl
import common.ast.NumberNode
import common.ast.OperatorNode
import common.ast.*
import common.token.*

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
                    5,
                    Token("5", ValueNumber, Position(1,10), Position(1,11)),
                    NumberNode,
                    null
                ),
                ASTNodeImpl(
                    7,
                    Token("7", ValueNumber, Position(1,12), Position(1,13)),
                    NumberNode,
                    null
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
                null // No children for identifier node
            ),
            ASTNodeImpl(
                5,
                Token("5", ValueNumber, Position(1, 17), Position(1, 18)),
                NumberNode,
                null
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
                null // No children for identifier node
            ),
            ASTNodeImpl(
                5,
                Token("5", ValueNumber, Position(2, 17), Position(2, 18)),
                NumberNode,
                null
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
                        null
                    ),
                    ASTNodeImpl(
                        "b",
                        Token("b", Identifier, Position(3, 12), Position(3, 13)),
                        IdentifierNode,
                        null
                    )
                )
            )
        )
    )
)
