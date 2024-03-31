package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.NumberNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.PrintlnKeyword
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TypeNumber
import edu.austral.ingsis.gradle.common.token.TypeString
import edu.austral.ingsis.gradle.common.token.ValueNumber
import edu.austral.ingsis.gradle.common.token.ValueString
import edu.austral.ingsis.gradle.formatter.util.RuleParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FormatterTest {
    private val rules = RuleParser().parseRulesFromFile("src/test/kotlin/resources/config.json")
    private val formatter = Formatter(rules)

    @Test
    fun test001_assignationDeclaration() {
        //    let a : number = 5;
        //    5 + 5;
        val output008 =
            listOf(
                ASTNodeImpl(
                    "=",
                    Token("=", Assignation, Position(1, 16), Position(1, 17)),
                    AssignationNode,
                    listOf(
                        ASTNodeImpl(
                            "a",
                            Token("a", Identifier, Position(1, 5), Position(1, 6)),
                            IdentifierNode,
                            listOf(
                                ASTNodeImpl(
                                    "let",
                                    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                                    KeywordNode,
                                    emptyList(),
                                ),
                                ASTNodeImpl(
                                    "number",
                                    Token("number", TypeNumber, Position(1, 9), Position(1, 15)),
                                    TypeNode,
                                    emptyList(),
                                ),
                            ),
                        ),
                        ASTNodeImpl(
                            5.0,
                            Token("5", ValueNumber, Position(1, 18), Position(1, 19)),
                            NumberNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "+",
                    Token("+", Plus, Position(2, 3), Position(2, 4)),
                    OperatorNode,
                    listOf(
                        ASTNodeImpl(
                            5.0,
                            Token("5", ValueNumber, Position(2, 1), Position(2, 2)),
                            NumberNode,
                            emptyList(),
                        ),
                        ASTNodeImpl(
                            5.0,
                            Token("5", ValueNumber, Position(2, 5), Position(2, 6)),
                            NumberNode,
                            emptyList(),
                        ),
                    ),
                ),
            )
        val node = ASTNodeImpl("Program", null, ProgramNode, output008)
        val formattedCode = formatter.format(node)
        val expected = "let a : number = 5.0;" + "\n" + "5.0 + 5.0;"
        assertEquals(expected, formattedCode)
    }

    @Test
    fun test002_x() {
        //    test
        //    let a: string = "hola"
        //    let b: string = "loco"
        //
        //    println (a+b)
        val output012 =
            listOf(
                ASTNodeImpl(
                    "=",
                    Token("=", Assignation, Position(1, 15), Position(1, 16)),
                    AssignationNode,
                    listOf(
                        ASTNodeImpl(
                            "a",
                            Token("a", Identifier, Position(1, 5), Position(1, 6)),
                            IdentifierNode,
                            listOf(
                                ASTNodeImpl(
                                    "let",
                                    Token("let", LetKeyword, Position(1, 1), Position(1, 4)),
                                    KeywordNode,
                                    emptyList(),
                                ),
                                ASTNodeImpl(
                                    "string",
                                    Token("string", TypeString, Position(1, 8), Position(1, 14)),
                                    TypeNode,
                                    emptyList(),
                                ),
                            ),
                        ),
                        ASTNodeImpl(
                            "hola",
                            Token("hola", ValueString, Position(1, 17), Position(1, 18)),
                            StringNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "=",
                    Token("=", Assignation, Position(2, 15), Position(2, 16)),
                    AssignationNode,
                    listOf(
                        ASTNodeImpl(
                            "b",
                            Token("b", Identifier, Position(2, 5), Position(2, 6)),
                            IdentifierNode,
                            listOf(
                                ASTNodeImpl(
                                    "let",
                                    Token("let", LetKeyword, Position(2, 1), Position(2, 4)),
                                    KeywordNode,
                                    emptyList(),
                                ),
                                ASTNodeImpl(
                                    "string",
                                    Token("string", TypeString, Position(2, 8), Position(2, 14)),
                                    TypeNode,
                                    emptyList(),
                                ),
                            ),
                        ),
                        ASTNodeImpl(
                            "loco",
                            Token("loco", ValueString, Position(2, 17), Position(2, 18)),
                            StringNode,
                            emptyList(),
                        ),
                    ),
                ),
                ASTNodeImpl(
                    "println",
                    Token("println", PrintlnKeyword, Position(3, 1), Position(3, 8)),
                    PrintLnNode,
                    listOf(
                        ASTNodeImpl(
                            "+",
                            Token("+", Plus, Position(3, 11), Position(3, 12)),
                            OperatorNode,
                            listOf(
                                ASTNodeImpl(
                                    "a",
                                    Token("a", Identifier, Position(3, 10), Position(3, 11)),
                                    IdentifierNode,
                                    emptyList(),
                                ),
                                ASTNodeImpl(
                                    "b",
                                    Token("b", Identifier, Position(3, 12), Position(3, 13)),
                                    IdentifierNode,
                                    emptyList(),
                                ),
                            ),
                        ),
                    ),
                ),
            )
        val node = ASTNodeImpl("Program", null, ProgramNode, output012)
        val formattedCode = formatter.format(node)
        val expected = "let a : string = \"hola\";" + "\n" + "let b : string = \"loco\";" + "\n\n" + "println a + b;"
        assertEquals(expected, formattedCode)
    }
}
