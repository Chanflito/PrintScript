package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.common.ast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.ConstKeywordNode
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.DivideNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.IfStatement
import edu.austral.ingsis.gradle.common.ast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

val declaration =
    DeclarationNode(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("name", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val declaration2 =
    DeclarationNode(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberNodeType,
        IdentifierNode("age", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_001 =
    ReAssignationNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringLiteral("name", TokenPosition(Position(0, 0), Position(0, 0))),
        IdentifierNode("name", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_002 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringLiteral("Hello world", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_003 =
    DeclarationAssignation(
        ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberNodeType,
        IdentifierNode("age", TokenPosition(Position(0, 0), Position(0, 0))),
        NumberLiteralNode(20, TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_003let =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberNodeType,
        IdentifierNode("age", TokenPosition(Position(0, 0), Position(0, 0))),
        NumberLiteralNode(
            20,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_004 =
    ReAssignationNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberLiteralNode(30, TokenPosition(Position(0, 0), Position(0, 0))),
        IdentifierNode("age", TokenPosition(Position(0, 0), Position(0, 0))),
    )

val input_005 =
    SumNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberLiteralNode(2, TokenPosition(Position(0, 0), Position(0, 0))),
        NumberLiteralNode(
            2,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_006 =
    SubtractNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberLiteralNode(2, TokenPosition(Position(0, 0), Position(0, 0))),
        NumberLiteralNode(
            2,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_007 =
    MultiplyNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberLiteralNode(2, TokenPosition(Position(0, 0), Position(0, 0))),
        NumberLiteralNode(
            2,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_008 =
    DivideNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberLiteralNode(2, TokenPosition(Position(0, 0), Position(0, 0))),
        NumberLiteralNode(
            2,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_009 =
    IfStatement(
        TokenPosition(Position(0, 0), Position(0, 0)),
        BooleanLiteralNode(true, TokenPosition(Position(0, 0), Position(0, 0))),
        BlockNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            listOf(
                input_004,
            ),
        ),
    )

val input_009else =
    IfElseStatement(
        TokenPosition(Position(0, 0), Position(0, 0)),
        BooleanLiteralNode(false, TokenPosition(Position(0, 0), Position(0, 0))),
        BlockNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            listOf(),
        ),
        BlockNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            listOf(
                input_004,
            ),
        ),
    )

val input_010 =
    DeclarationAssignation(
        ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("input", TokenPosition(Position(0, 0), Position(0, 0))),
        ReadInputNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            StringLiteral(
                "input",
                TokenPosition(Position(0, 0), Position(0, 0)),
            ),
        ),
    )

val input_011 =
    DeclarationAssignation(
        ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("input", TokenPosition(Position(0, 0), Position(0, 0))),
        ReadEnvNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            "PATH",
        ),
    )

val input_012 =
    SumNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringLiteral(
            "Hello ",
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
        NumberLiteralNode(
            12,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_013 =
    DeclarationAssignation(
        LetKeywordNode(
            TokenPosition(Position(1, 1), Position(1, 4)),
        ),
        TokenPosition(Position(1, 16), Position(1, 17)),
        NumberNodeType,
        IdentifierNode(
            "b",
            TokenPosition(Position(1, 5), Position(1, 6)),
        ),
        NumberLiteralNode(
            5.0,
            TokenPosition(Position(1, 18), Position(1, 19)),
        ),
    )

val input_014 =
    DeclarationAssignation(
        ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        StringNodeType,
        IdentifierNode("name", TokenPosition(Position(0, 0), Position(0, 0))),
        ReadInputNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            StringLiteral(
                "Name: ",
                TokenPosition(Position(0, 0), Position(0, 0)),
            ),
        ),
    )

val input_015 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        SumNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            StringLiteral(
                "Hello ",
                TokenPosition(Position(0, 0), Position(0, 0)),
            ),
            SumNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                IdentifierNode(
                    "name",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
                StringLiteral(
                    "!",
                    TokenPosition(Position(0, 0), Position(0, 0)),
                ),
            ),
        ),
    )

val input_016 =
    DeclarationAssignation(
        ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberNodeType,
        IdentifierNode("number", TokenPosition(Position(0, 0), Position(0, 0))),
        ReadInputNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            StringLiteral(
                "Enter number: ",
                TokenPosition(Position(0, 0), Position(0, 0)),
            ),
        ),
    )

val input_017 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberLiteralNode(
            9,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_cli1 =
    DeclarationAssignation(
        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberNodeType,
        IdentifierNode(
            "a",
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
        NumberLiteralNode(
            5.0,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_cli2 =
    DeclarationAssignation(
        ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
        TokenPosition(Position(0, 0), Position(0, 0)),
        NumberNodeType,
        IdentifierNode(
            "b",
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
        NumberLiteralNode(
            9.0,
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_cli3 =
    ReAssignationNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        DivideNode(
            TokenPosition(Position(0, 0), Position(0, 0)),
            IdentifierNode(
                "a",
                TokenPosition(Position(0, 0), Position(0, 0)),
            ),
            IdentifierNode(
                "b",
                TokenPosition(Position(0, 0), Position(0, 0)),
            ),
        ),
        IdentifierNode(
            "a",
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )

val input_cli4 =
    PrintLnNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        IdentifierNode(
            "a",
            TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    )
