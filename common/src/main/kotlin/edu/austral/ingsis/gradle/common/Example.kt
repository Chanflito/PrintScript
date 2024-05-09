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
import edu.austral.ingsis.gradle.common.token.defaultTokenPosition

// let a : string ="hola"

val declarationAssignation =
    DeclarationAssignation(
        keyword = LetKeywordNode(defaultTokenPosition()),
        tokenPosition = defaultTokenPosition(),
        nodeType = StringNodeType,
        identifierNode =
            IdentifierNode(
                name = "x",
                tokenPosition = defaultTokenPosition(),
            ),
        expression =
            StringLiteral(
                "hola",
                tokenPosition = defaultTokenPosition(),
            ),
    )

// 5+5.2

val simpleExpression =
    SumNode(
        tokenPosition = defaultTokenPosition(),
        NumberLiteralNode(5, defaultTokenPosition()),
        NumberLiteralNode(5.2, defaultTokenPosition()),
    )

// println(5+"hola")

val printExample =
    PrintLnNode(
        defaultTokenPosition(),
        SumNode(
            defaultTokenPosition(),
            NumberLiteralNode(5, defaultTokenPosition()),
            StringLiteral("hola", defaultTokenPosition()),
        ),
    )

// let a : string = readInput ("aaa")
val readInput =
    DeclarationAssignation(
        keyword = LetKeywordNode(defaultTokenPosition()),
        tokenPosition = defaultTokenPosition(),
        nodeType = StringNodeType,
        identifierNode =
            IdentifierNode(
                name = "x",
                tokenPosition = defaultTokenPosition(),
            ),
        expression =
            ReadInputNode(
                defaultTokenPosition(),
                StringLiteral("aaa", defaultTokenPosition()),
            ),
    )

// let a : string= b;
// a
// let a : string;
// println(a)
// a + b
// en un if
// block...
// else block...
// en un readinput
// reassingation
