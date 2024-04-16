package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

val formatter =
    ComposeFormatter(
        listOf(
            DeclarationAssignationFormatter(),
            ReAssignationFormatter(),
            IfStatementFormatter(),
            IfElseStatementFormatter(),
        ),
    )

class FormatterTest {
    @Test
    fun test001_formatDeclarationAssignation() {
        val ast =
            ProgramNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                listOf(
                    DeclarationAssignation(
                        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        StringNodeType,
                        IdentifierNode("aBlue", TokenPosition(Position(0, 0), Position(0, 0))),
                        StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
                    ),
                ),
            )

        val formatted = formatter.format(ast)
        assertEquals(expected = "let aBlue : String = \"blue\"", actual = formatted)
    }

    @Test
    fun test002_formatIfStatement() {
        val ast =
            ProgramNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                listOf(
                    IfStatement(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        condition = IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                        ifBlock =
                            BlockNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                listOf(
                                    PrintLnNode(
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                        SumNode(
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
                                    ),
                                ),
                            ),
                    ),
                ),
            )

        val formatted = formatter.format(ast)
        assertEquals(expected = "if (a) {\nprintln(a + b)\n}", actual = formatted)
    }

    @Test
    fun test003_formatIfElseStatement() {
        val ast =
            ProgramNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                listOf(
                    IfElseStatement(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        condition = IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                        ifBlock =
                            BlockNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                listOf(
                                    PrintLnNode(
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                        SumNode(
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
                                    ),
                                ),
                            ),
                        elseBlock =
                            BlockNode(
                                TokenPosition(Position(0, 0), Position(0, 0)),
                                listOf(
                                    PrintLnNode(
                                        TokenPosition(Position(0, 0), Position(0, 0)),
                                        SubtractNode(
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
                                    ),
                                ),
                            ),
                    ),
                ),
            )

        val formatted = formatter.format(ast)
        assertEquals(expected = "if (a) {\nprintln(a + b)\n}else {\nprintln(a - b)\n}", actual = formatted)
    }

    @Test
    fun test004_formatReAssignation() {
        val ast =
            ProgramNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                listOf(
                    ReAssignationNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        NumberLiteralNode(
                            1,
                            TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                        IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                    ),
                ),
            )

        val formatted = formatter.format(ast)
        assertEquals(expected = "a = 1", actual = formatted)
    }

    @Test
    fun test005_formatDeclarationAssignation_ReAssignation_DeclarationAssignation() {
        val ast =
            ProgramNode(
                TokenPosition(Position(0, 0), Position(0, 0)),
                listOf(
                    DeclarationAssignation(
                        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        StringNodeType,
                        IdentifierNode("aBlue", TokenPosition(Position(0, 0), Position(0, 0))),
                        StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
                    ),
                    ReAssignationNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        NumberLiteralNode(
                            1,
                            TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                        IdentifierNode("a", TokenPosition(Position(0, 0), Position(0, 0))),
                    ),
                    DeclarationAssignation(
                        LetKeywordNode(TokenPosition(Position(0, 0), Position(0, 0))),
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        StringNodeType,
                        IdentifierNode("aBlue", TokenPosition(Position(0, 0), Position(0, 0))),
                        StringLiteral("blue", TokenPosition(Position(0, 0), Position(0, 0))),
                    ),
                ),
            )

        val formatted = formatter.format(ast)
        assertEquals(expected = "let aBlue : String = \"blue\"\na = 1\nlet aBlue : String = \"blue\"", actual = formatted)
    }
}
