package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.NumberNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.common.token.Assignation
import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.LetKeyword
import edu.austral.ingsis.gradle.common.token.Plus
import edu.austral.ingsis.gradle.common.token.Position
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.TypeNumber
import edu.austral.ingsis.gradle.common.token.ValueNumber
import edu.austral.ingsis.gradle.formatter.util.Rule
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
        assertEquals(formattedCode, "let a : number = 5.0;" + "\n" + "5.0 + 5.0;")
    }
}
