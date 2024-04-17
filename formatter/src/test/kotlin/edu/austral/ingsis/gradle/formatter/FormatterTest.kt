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
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.TokenPosition
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FormatterTest {
    @Test
    fun test001_formatDeclarationAssignation() {
        val formatter = createDefaultFormatter()
        val rules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
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

        val formatted = formatter.format(ast, rules)
        assertEquals(expected = "let aBlue : String = \"blue\";", actual = formatted)
    }

    @Test
    fun test002_formatIfStatement() {
        val formatter = createDefaultFormatter()
        val rules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
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

        val formatted = formatter.format(ast, rules)
        assertEquals(expected = "if (a) {\nprintln(a + b);\n}", actual = formatted)
    }

    @Test
    fun test003_formatIfElseStatement() {
        val formatter = createDefaultFormatter()
        val rules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
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

        val formatted = formatter.format(ast, rules)
        assertEquals(expected = "if (a) {\nprintln(a + b);\n}else {\nprintln(a - b);\n}", actual = formatted)
    }

    @Test
    fun test004_formatReAssignation() {
        val formatter = createDefaultFormatter()
        val rules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
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

        val formatted = formatter.format(ast, rules)
        assertEquals(expected = "a = 1;", actual = formatted)
    }

    @Test
    fun test005_formatDeclarationAssignation_ReAssignation_DeclarationAssignation() {
        val formatter = createDefaultFormatter()
        val rules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))
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

        val formatted = formatter.format(ast, rules)
        assertEquals(expected = "let aBlue : String = \"blue\";\na = 1;\nlet aBlue : String = \"blue\";", actual = formatted)
    }

    @Test
    fun test006_testPrintLn_ReadInput() {
        val formatter = createDefaultFormatter()
        val rules = ComposeRule(createDefaultRules("src/test/resources/config_001.json"))

        val ast =
            ProgramNode(
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
                    ReadInputNode(
                        TokenPosition(Position(0, 0), Position(0, 0)),
                        IdentifierNode(
                            "Introduzca un valor",
                            TokenPosition(Position(0, 0), Position(0, 0)),
                        ),
                    ),
                ),
            )

        val formatted = formatter.format(ast, rules)
        assertEquals(expected = "println(a + b);\nreadInput(Introduzca un valor);", actual = formatted)
    }
}
