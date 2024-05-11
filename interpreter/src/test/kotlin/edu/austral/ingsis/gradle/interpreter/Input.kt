package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.BlockNode
import edu.austral.ingsis.gradle.common.ast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.ConstKeywordNode
import edu.austral.ingsis.gradle.common.ast.DeclarationAssignationNode
import edu.austral.ingsis.gradle.common.ast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.DivideNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.IfElseStatementNode
import edu.austral.ingsis.gradle.common.ast.IfStatementNode
import edu.austral.ingsis.gradle.common.ast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.StringLiteralNode
import edu.austral.ingsis.gradle.common.ast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition
import edu.austral.ingsis.gradle.common.token.defaultTokenPosition

val declaration =
    DeclarationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("name", defaultTokenPosition()),
    )

val declaration2 =
    DeclarationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        NumberNodeType,
        IdentifierNode("age", defaultTokenPosition()),
    )

val input_001 =
    ReAssignationNode(
        defaultTokenPosition(),
        StringLiteralNode("name", defaultTokenPosition()),
        IdentifierNode("name", defaultTokenPosition()),
    )

val input_002 =
    PrintLnNode(
        defaultTokenPosition(),
        StringLiteralNode("Hello world", defaultTokenPosition()),
    )

val input_003 =
    DeclarationAssignationNode(
        ConstKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        NumberNodeType,
        IdentifierNode("age", defaultTokenPosition()),
        NumberLiteralNode(20, defaultTokenPosition()),
    )

val input_003let =
    DeclarationAssignationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        NumberNodeType,
        IdentifierNode("age", defaultTokenPosition()),
        NumberLiteralNode(
            20,
            defaultTokenPosition(),
        ),
    )

val input_004 =
    ReAssignationNode(
        defaultTokenPosition(),
        NumberLiteralNode(30, defaultTokenPosition()),
        IdentifierNode("age", defaultTokenPosition()),
    )

val input_005 =
    SumNode(
        defaultTokenPosition(),
        NumberLiteralNode(2, defaultTokenPosition()),
        NumberLiteralNode(
            2,
            defaultTokenPosition(),
        ),
    )

val input_006 =
    SubtractNode(
        defaultTokenPosition(),
        NumberLiteralNode(2, defaultTokenPosition()),
        NumberLiteralNode(
            2,
            defaultTokenPosition(),
        ),
    )

val input_007 =
    MultiplyNode(
        defaultTokenPosition(),
        NumberLiteralNode(2, defaultTokenPosition()),
        NumberLiteralNode(
            2,
            defaultTokenPosition(),
        ),
    )

val input_008 =
    DivideNode(
        defaultTokenPosition(),
        NumberLiteralNode(2, defaultTokenPosition()),
        NumberLiteralNode(
            2,
            defaultTokenPosition(),
        ),
    )

val input_009 =
    IfStatementNode(
        defaultTokenPosition(),
        BooleanLiteralNode(true, defaultTokenPosition()),
        BlockNode(
            defaultTokenPosition(),
            listOf(
                input_004,
            ),
        ),
    )

val input_009else =
    IfElseStatementNode(
        defaultTokenPosition(),
        BooleanLiteralNode(false, defaultTokenPosition()),
        BlockNode(
            defaultTokenPosition(),
            listOf(),
        ),
        BlockNode(
            defaultTokenPosition(),
            listOf(
                input_004,
            ),
        ),
    )

val input_010 =
    DeclarationAssignationNode(
        ConstKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("input", defaultTokenPosition()),
        ReadInputNode(
            defaultTokenPosition(),
            StringLiteralNode(
                "input",
                defaultTokenPosition(),
            ),
        ),
    )

val input_011 =
    DeclarationAssignationNode(
        ConstKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("input", defaultTokenPosition()),
        ReadEnvNode(
            defaultTokenPosition(),
            "PATH",
        ),
    )

val input_012 =
    SumNode(
        defaultTokenPosition(),
        StringLiteralNode(
            "Hello ",
            defaultTokenPosition(),
        ),
        NumberLiteralNode(
            12,
            defaultTokenPosition(),
        ),
    )

val input_013 =
    DeclarationAssignationNode(
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
    DeclarationAssignationNode(
        ConstKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("name", defaultTokenPosition()),
        ReadInputNode(
            defaultTokenPosition(),
            StringLiteralNode(
                "Name: ",
                defaultTokenPosition(),
            ),
        ),
    )

val input_015 =
    PrintLnNode(
        defaultTokenPosition(),
        SumNode(
            defaultTokenPosition(),
            StringLiteralNode(
                "Hello ",
                defaultTokenPosition(),
            ),
            SumNode(
                defaultTokenPosition(),
                IdentifierNode(
                    "name",
                    defaultTokenPosition(),
                ),
                StringLiteralNode(
                    "!",
                    defaultTokenPosition(),
                ),
            ),
        ),
    )

val input_016 =
    DeclarationAssignationNode(
        ConstKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        NumberNodeType,
        IdentifierNode("number", defaultTokenPosition()),
        ReadInputNode(
            defaultTokenPosition(),
            StringLiteralNode(
                "Enter number: ",
                defaultTokenPosition(),
            ),
        ),
    )

val input_017 =
    PrintLnNode(
        defaultTokenPosition(),
        NumberLiteralNode(
            9,
            defaultTokenPosition(),
        ),
    )

val input_cli1 =
    DeclarationAssignationNode(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        NumberNodeType,
        IdentifierNode(
            "a",
            defaultTokenPosition(),
        ),
        NumberLiteralNode(
            5.0,
            defaultTokenPosition(),
        ),
    )

val input_cli2 =
    DeclarationAssignationNode(
        ConstKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        NumberNodeType,
        IdentifierNode(
            "b",
            defaultTokenPosition(),
        ),
        NumberLiteralNode(
            9.0,
            defaultTokenPosition(),
        ),
    )

val input_cli3 =
    ReAssignationNode(
        defaultTokenPosition(),
        DivideNode(
            defaultTokenPosition(),
            IdentifierNode(
                "a",
                defaultTokenPosition(),
            ),
            IdentifierNode(
                "b",
                defaultTokenPosition(),
            ),
        ),
        IdentifierNode(
            "a",
            defaultTokenPosition(),
        ),
    )

val input_cli4 =
    PrintLnNode(
        defaultTokenPosition(),
        IdentifierNode(
            "a",
            defaultTokenPosition(),
        ),
    )
