package edu.austral.ingsis.gradle.interpreter
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.ConstKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
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

val input999 =
    PrintLnNode(
        tokenPosition = TokenPosition(startPosition = Position(row = 2, column = 1), endPosition = Position(row = 2, column = 8)),
        expression =
            IdentifierNode(
                name = "b",
                tokenPosition = TokenPosition(startPosition = Position(row = 2, column = 9), endPosition = Position(row = 2, column = 10)),
            ),
    )

val input_cli1= DeclarationAssignation(
    LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
    TokenPosition(Position(0, 0), Position(0, 0)),
    NumberNodeType,
    IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0)),
    ),
    NumberLiteralNode(5.0, TokenPosition(Position(0, 0), Position(0, 0)),
    ),
)

val input_cli2 = DeclarationAssignation(
    ConstKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
    TokenPosition(Position(0, 0), Position(0, 0)),
    NumberNodeType,
    IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0)),
    ),
    NumberLiteralNode(9.0, TokenPosition(Position(0, 0), Position(0, 0)),
    ),
)

val input_cli3 = ReAssignationNode(
    TokenPosition(Position(0, 0), Position(0, 0)),
    DivideNode(
        TokenPosition(Position(0, 0), Position(0, 0)),
        IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0)),
        ),
        IdentifierNode("b", TokenPosition(Position(0, 0), Position(0, 0)),
        ),
    ),
    IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0)),
    ),
)

val input_cli4 = PrintLnNode(
    TokenPosition(Position(0, 0), Position(0, 0)),
    IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0)),
    ),
)
