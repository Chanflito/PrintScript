package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.NumberNode
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
import edu.austral.ingsis.gradle.common.token.TokenPosition
import edu.austral.ingsis.gradle.common.token.TypeNumber
import edu.austral.ingsis.gradle.common.token.TypeString
import edu.austral.ingsis.gradle.common.token.ValueNumber
import edu.austral.ingsis.gradle.common.token.ValueString

val input_001 =
    listOf(
        ASTNodeImpl(
            "println",
            Token("println", PrintlnKeyword, TokenPosition(Position(1, 1), Position(1, 8))),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "+",
                    Token("+", Plus, TokenPosition(Position(1, 11), Position(1, 12))),
                    OperatorNode,
                    listOf(
                        ASTNodeImpl(
                            5.0,
                            Token("5", ValueNumber, TokenPosition(Position(1, 10), Position(1, 11))),
                            NumberNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            7.0,
                            Token("7", ValueNumber, TokenPosition(Position(1, 12), Position(1, 13))),
                            NumberNode,
                            emptyList(),
                        ),
                    ),
                ),
            ),
        ),
    )

val input_002 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
                    IdentifierNode,
                    emptyList(),
                ),
                ASTNodeImpl(
                    5.0,
                    Token("5", ValueNumber, TokenPosition(Position(1, 17), Position(1, 18))),
                    NumberNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "b",
                    Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
                    IdentifierNode,
                    emptyList(),
                ),
                ASTNodeImpl(
                    5.0,
                    Token("5", ValueNumber, TokenPosition(Position(2, 17), Position(2, 18))),
                    NumberNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "println",
            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "+",
                    Token("+", Plus, TokenPosition(Position(3, 11), Position(3, 12))),
                    OperatorNode,
                    listOf(
                        ASTNodeImpl(
                            "a",
                            Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
                            IdentifierNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "b",
                            Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
                            IdentifierNode,
                            emptyList(),
                        ),
                    ),
                ),
            ),
        ),
    )

val input_003 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "number",
                            Token("number", TypeNumber, TokenPosition(Position(1, 8), Position(1, 14))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    5.0,
                    Token("5", ValueNumber, TokenPosition(Position(1, 17), Position(1, 18))),
                    NumberNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "b",
                    Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "number",
                            Token("number", TypeNumber, TokenPosition(Position(1, 8), Position(1, 14))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    5.0,
                    Token("5", ValueNumber, TokenPosition(Position(2, 17), Position(2, 18))),
                    NumberNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "println",
            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "+",
                    Token("+", Plus, TokenPosition(Position(3, 11), Position(3, 12))),
                    OperatorNode,
                    listOf(
                        ASTNodeImpl(
                            "a",
                            Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
                            IdentifierNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "b",
                            Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
                            IdentifierNode,
                            emptyList(),
                        ),
                    ),
                ),
            ),
        ),
    )

val input_004 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "number",
                            Token("number", TypeNumber, TokenPosition(Position(1, 8), Position(1, 14))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    5.0,
                    Token("5", ValueNumber, TokenPosition(Position(1, 17), Position(1, 18))),
                    NumberNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "number",
                            Token("number", TypeNumber, TokenPosition(Position(1, 8), Position(1, 14))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    5.0,
                    Token("5", ValueNumber, TokenPosition(Position(2, 17), Position(2, 18))),
                    NumberNode,
                    emptyList(),
                ),
            ),
        ),
    )

val input_005 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "string",
                            Token("string", TypeString, TokenPosition(Position(1, 8), Position(1, 14))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "hola",
                    Token("hola", ValueString, TokenPosition(Position(1, 17), Position(1, 18))),
                    StringNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
                    IdentifierNode,
                    emptyList(),
                ),
                ASTNodeImpl(
                    "loco",
                    Token("loco", ValueString, TokenPosition(Position(2, 17), Position(2, 18))),
                    StringNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "println",
            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
                    IdentifierNode,
                    emptyList(),
                ),
            ),
        ),
    )

val input_006 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a",
                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "string",
                            Token("string", TypeString, TokenPosition(Position(1, 8), Position(1, 14))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "hola",
                    Token("hola", ValueString, TokenPosition(Position(1, 17), Position(1, 18))),
                    StringNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "b",
                    Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
                    IdentifierNode,
                    listOf(
                        ASTNodeImpl(
                            "let",
                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
                            KeywordNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "string",
                            Token("string", TypeString, TokenPosition(Position(1, 8), Position(1, 14))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "loco",
                    Token("loco", ValueString, TokenPosition(Position(2, 17), Position(2, 18))),
                    StringNode,
                    emptyList(),
                ),
            ),
        ),
        ASTNodeImpl(
            "println",
            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "+",
                    Token("+", Plus, TokenPosition(Position(3, 11), Position(3, 12))),
                    OperatorNode,
                    listOf(
                        ASTNodeImpl(
                            "a",
                            Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
                            IdentifierNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            "b",
                            Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
                            IdentifierNode,
                            emptyList(),
                        ),
                    ),
                ),
            ),
        ),
    )
