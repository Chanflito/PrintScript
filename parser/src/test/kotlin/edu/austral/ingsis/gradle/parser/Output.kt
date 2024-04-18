package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val output_001 = NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1)))

val output_002 =
    SumNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_003 =
    SumNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        StringLiteral("hola", TokenPosition(Position(1, 1), Position(1, 1))),
        StringLiteral("loco", TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_004 =
    MultiplyNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_005 =
    DivideNode(
        TokenPosition(Position(1, 1), Position(1, 1)),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 1), Position(1, 1))),
    )

val output_006 =
    DeclarationNode(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 7), Position(1, 8)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
    )

val output_007 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 16), Position(1, 17)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
        NumberLiteralNode(5.0, TokenPosition(Position(1, 18), Position(1, 19))),
    )

val output_008 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(1, 1), Position(1, 4))),
        TokenPosition(Position(1, 16), Position(1, 17)),
        NumberNodeType,
        IdentifierNode("a", TokenPosition(Position(1, 5), Position(1, 6))),
        NumberLiteralNode(
            5.0,
            TokenPosition(Position(1, 18), Position(1, 19)),
        ),
    )

//
val output_009 =
    PrintLnNode(
        TokenPosition(Position(1, 1), Position(1, 8)),
        SumNode(
            TokenPosition(Position(1, 12), Position(1, 12)),
            NumberLiteralNode(5.0, TokenPosition(Position(1, 10), Position(1, 11))),
            NumberLiteralNode(7.0, TokenPosition(Position(1, 12), Position(1, 13))),
        ),
    )

//
val output_010 =
    MultiplyNode(
        TokenPosition(Position(1, 6), Position(1, 7)),
        SumNode(
            TokenPosition(Position(1, 3), Position(1, 4)),
            NumberLiteralNode(5.0, TokenPosition(Position(1, 2), Position(1, 3))),
            NumberLiteralNode(7.0, TokenPosition(Position(1, 4), Position(1, 5))),
        ),
        NumberLiteralNode(
            4.0,
            TokenPosition(Position(1, 7), Position(1, 8)),
        ),
    )
//
// val output_011 =
//    listOf(
//        ASTNodeImpl(
//            "=",
//            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
//            AssignationNode,
//            listOf(
//                ASTNodeImpl(
//                    "a",
//                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
//                    edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                    listOf(
//                        ASTNodeImpl(
//                            "let",
//                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
//                            KeywordNode,
//                            emptyList(),
//                        ),
//                        ASTNodeImpl(
//                            "number",
//                            Token("number", NumberType, TokenPosition(Position(1, 8), Position(1, 14))),
//                            TypeNode,
//                            emptyList(),
//                        ),
//                    ),
//                ),
//                ASTNodeImpl(
//                    5.0,
//                    Token("5", NumberValue, TokenPosition(Position(1, 17), Position(1, 18))),
//                    NumberNode,
//                    emptyList(),
//                ),
//            ),
//        ),
//        ASTNodeImpl(
//            "=",
//            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
//            AssignationNode,
//            listOf(
//                ASTNodeImpl(
//                    "b",
//                    Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
//                    edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                    listOf(
//                        ASTNodeImpl(
//                            "let",
//                            Token("let", LetKeyword, TokenPosition(Position(2, 1), Position(2, 4))),
//                            KeywordNode,
//                            emptyList(),
//                        ),
//                        ASTNodeImpl(
//                            "number",
//                            Token("number", NumberType, TokenPosition(Position(2, 8), Position(2, 14))),
//                            TypeNode,
//                            emptyList(),
//                        ),
//                    ),
//                ),
//                ASTNodeImpl(
//                    5.0,
//                    Token("5", NumberValue, TokenPosition(Position(2, 17), Position(2, 18))),
//                    NumberNode,
//                    emptyList(),
//                ),
//            ),
//        ),
//        ASTNodeImpl(
//            "println",
//            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
//            PrintLnNode,
//            listOf(
//                ASTNodeImpl(
//                    "+",
//                    Token("+", Plus, TokenPosition(Position(3, 11), Position(3, 12))),
//                    OperatorNode,
//                    listOf(
//                        ASTNodeImpl(
//                            "a",
//                            Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
//                            edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                            emptyList(),
//                        ),
//                        ASTNodeImpl(
//                            "b",
//                            Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
//                            edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                            emptyList(),
//                        ),
//                    ),
//                ),
//            ),
//        ),
//    )
//
// val output_012 =
//    listOf(
//        ASTNodeImpl(
//            "=",
//            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
//            AssignationNode,
//            listOf(
//                ASTNodeImpl(
//                    "a",
//                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
//                    edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                    listOf(
//                        ASTNodeImpl(
//                            "let",
//                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
//                            KeywordNode,
//                            emptyList(),
//                        ),
//                        ASTNodeImpl(
//                            "string",
//                            Token("string", StringType, TokenPosition(Position(1, 8), Position(1, 14))),
//                            TypeNode,
//                            emptyList(),
//                        ),
//                    ),
//                ),
//                ASTNodeImpl(
//                    "hola",
//                    Token("hola", StringValue, TokenPosition(Position(1, 17), Position(1, 18))),
//                    StringNode,
//                    emptyList(),
//                ),
//            ),
//        ),
//        ASTNodeImpl(
//            "=",
//            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
//            AssignationNode,
//            listOf(
//                ASTNodeImpl(
//                    "b",
//                    Token("b", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
//                    edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                    listOf(
//                        ASTNodeImpl(
//                            "let",
//                            Token("let", LetKeyword, TokenPosition(Position(2, 1), Position(2, 4))),
//                            KeywordNode,
//                            emptyList(),
//                        ),
//                        ASTNodeImpl(
//                            "string",
//                            Token("string", StringType, TokenPosition(Position(2, 8), Position(2, 14))),
//                            TypeNode,
//                            emptyList(),
//                        ),
//                    ),
//                ),
//                ASTNodeImpl(
//                    "loco",
//                    Token("loco", StringValue, TokenPosition(Position(2, 17), Position(2, 18))),
//                    StringNode,
//                    emptyList(),
//                ),
//            ),
//        ),
//        ASTNodeImpl(
//            "println",
//            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
//            PrintLnNode,
//            listOf(
//                ASTNodeImpl(
//                    "+",
//                    Token("+", Plus, TokenPosition(Position(3, 11), Position(3, 12))),
//                    OperatorNode,
//                    listOf(
//                        ASTNodeImpl(
//                            "a",
//                            Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
//                            edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                            emptyList(),
//                        ),
//                        ASTNodeImpl(
//                            "b",
//                            Token("b", Identifier, TokenPosition(Position(3, 12), Position(3, 13))),
//                            edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                            emptyList(),
//                        ),
//                    ),
//                ),
//            ),
//        ),
//    )
//
// val output_013 =
//    listOf(
//        ASTNodeImpl(
//            "=",
//            Token("=", Assignation, TokenPosition(Position(1, 15), Position(1, 16))),
//            AssignationNode,
//            listOf(
//                ASTNodeImpl(
//                    "a",
//                    Token("a", Identifier, TokenPosition(Position(1, 5), Position(1, 6))),
//                    edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                    listOf(
//                        ASTNodeImpl(
//                            "let",
//                            Token("let", LetKeyword, TokenPosition(Position(1, 1), Position(1, 4))),
//                            KeywordNode,
//                            emptyList(),
//                        ),
//                        ASTNodeImpl(
//                            "string",
//                            Token("string", StringType, TokenPosition(Position(1, 8), Position(1, 14))),
//                            TypeNode,
//                            emptyList(),
//                        ),
//                    ),
//                ),
//                ASTNodeImpl(
//                    "hola",
//                    Token("hola", StringValue, TokenPosition(Position(1, 17), Position(1, 18))),
//                    StringNode,
//                    emptyList(),
//                ),
//            ),
//        ),
//        ASTNodeImpl(
//            "=",
//            Token("=", Assignation, TokenPosition(Position(2, 15), Position(2, 16))),
//            AssignationNode,
//            listOf(
//                ASTNodeImpl(
//                    "a",
//                    Token("a", Identifier, TokenPosition(Position(2, 5), Position(2, 6))),
//                    edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                    emptyList(),
//                ),
//                ASTNodeImpl(
//                    "loco",
//                    Token("loco", StringValue, TokenPosition(Position(2, 17), Position(2, 18))),
//                    StringNode,
//                    emptyList(),
//                ),
//            ),
//        ),
//        ASTNodeImpl(
//            "println",
//            Token("println", PrintlnKeyword, TokenPosition(Position(3, 1), Position(3, 8))),
//            PrintLnNode,
//            listOf(
//                ASTNodeImpl(
//                    "a",
//                    Token("a", Identifier, TokenPosition(Position(3, 10), Position(3, 11))),
//                    edu.austral.ingsis.gradle.common.ast.IdentifierNode,
//                    emptyList(),
//                ),
//            ),
//        ),
//    )
