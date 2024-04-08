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
import edu.austral.ingsis.gradle.common.token.StringType
import edu.austral.ingsis.gradle.common.token.StringValue
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TokenPosition

val input_001 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(1, 16), Position(1, 17))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "aBlue",
                    Token("aBlue", Identifier, TokenPosition(Position(1, 5), Position(1, 10))),
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
                            Token("string", StringType, TokenPosition(Position(1, 12), Position(1, 18))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "blue",
                    Token("blue", StringValue, TokenPosition(Position(1, 18), Position(1, 19))),
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
            Token("=", Assignation, TokenPosition(Position(1, 16), Position(1, 17))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a_Blue",
                    Token("a_Blue", Identifier, TokenPosition(Position(1, 5), Position(1, 10))),
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
                            Token("string", StringType, TokenPosition(Position(1, 12), Position(1, 18))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "blue",
                    Token("blue", StringValue, TokenPosition(Position(1, 18), Position(1, 19))),
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
            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
            PrintLnNode,
            listOf(
                ASTNodeImpl(
                    "b",
                    Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
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

val input_005 =
    listOf(
        ASTNodeImpl(
            "=",
            Token("=", Assignation, TokenPosition(Position(1, 16), Position(1, 17))),
            AssignationNode,
            listOf(
                ASTNodeImpl(
                    "a_snake",
                    Token("a_snake", Identifier, TokenPosition(Position(1, 5), Position(1, 10))),
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
                            Token("string", StringType, TokenPosition(Position(1, 12), Position(1, 18))),
                            TypeNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "snake",
                    Token("snake", StringValue, TokenPosition(Position(1, 18), Position(1, 19))),
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
                    "a_snake",
                    Token("a_snake", Identifier, TokenPosition(Position(3, 12), Position(3, 19))),
                    IdentifierNode,
                    emptyList(),
                ),
            ),
        ),
    )

val input_006 =
    listOf(
        ASTNodeImpl(
            "aCamel",
            Token("aCamel", Identifier, TokenPosition(Position(1, 5), Position(1, 10))),
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
                    Token("string", StringType, TokenPosition(Position(1, 12), Position(1, 18))),
                    TypeNode,
                    emptyList(),
                ),
            ),
        ),
    )

val input_007 =
    listOf(
        ASTNodeImpl(
            "a_snake",
            Token("a_snake", Identifier, TokenPosition(Position(1, 5), Position(1, 10))),
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
                    Token("string", StringType, TokenPosition(Position(1, 12), Position(1, 18))),
                    TypeNode,
                    emptyList(),
                ),
            ),
        ),
    )
