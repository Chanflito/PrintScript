package edu.austral.ingsis.gradle.common

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition

// let a : string ="hola"

val declarationAssignation =
    DeclarationAssignation(
        keyword = LetKeywordNode(TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3))),
        tokenPosition = TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 20)),
        nodeType = StringNodeType,
        identifierNode =
            IdentifierNode(
                name = "x",
                tokenPosition = TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 0)),
            ),
        expression =
            StringLiteral(
                "hola",
                tokenPosition = TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 0)),
            ),
    )

// 5+5.2

val simpleExpression =
    SumNode(
        tokenPosition = TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3)),
        NumberLiteralNode(5, TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3))),
        NumberLiteralNode(5.2, TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3))),
    )

// println(5+"hola")

val printExample =
    PrintLnNode(
        TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3)),
        SumNode(
            TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3)),
            NumberLiteralNode(5, TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3))),
            StringLiteral("hola", TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3))),
        ),
    )

// let a : string = readInput ("aaa")
val readInput =
    DeclarationAssignation(
        keyword = LetKeywordNode(TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 3))),
        tokenPosition = TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 20)),
        nodeType = StringNodeType,
        identifierNode =
            IdentifierNode(
                name = "x",
                tokenPosition = TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 0)),
            ),
        expression =
            ReadInputNode(
                TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 20)),
                StringLiteral("aaa", TokenPosition(startPosition = Position(0, 0), endPosition = Position(0, 0))),
            ),
    )
