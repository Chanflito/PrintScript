import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.LetKeywordNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.common.token.defaultTokenPosition

val input001 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("aBlue", defaultTokenPosition()),
                StringLiteral("blue", defaultTokenPosition()),
            ),
        ),
    )

val input002 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            IfStatement(
                defaultTokenPosition(),
                condition = IdentifierNode("a", defaultTokenPosition()),
                ifBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            PrintLnNode(
                                defaultTokenPosition(),
                                SumNode(
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
                            ),
                            DeclarationAssignation(
                                LetKeywordNode(defaultTokenPosition()),
                                defaultTokenPosition(),
                                StringNodeType,
                                IdentifierNode("aBlue", defaultTokenPosition()),
                                StringLiteral("blue", defaultTokenPosition()),
                            ),
                        ),
                    ),
            ),
        ),
    )

val input003 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            IfElseStatement(
                defaultTokenPosition(),
                condition = IdentifierNode("a", defaultTokenPosition()),
                ifBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            PrintLnNode(
                                defaultTokenPosition(),
                                SumNode(
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
                            ),
                        ),
                    ),
                elseBlock =
                    BlockNode(
                        defaultTokenPosition(),
                        listOf(
                            PrintLnNode(
                                defaultTokenPosition(),
                                SubtractNode(
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
                            ),
                        ),
                    ),
            ),
        ),
    )

val input004 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            ReAssignationNode(
                defaultTokenPosition(),
                NumberLiteralNode(
                    1,
                    defaultTokenPosition(),
                ),
                IdentifierNode("a", defaultTokenPosition()),
            ),
        ),
    )

val input005 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("aBlue", defaultTokenPosition()),
                StringLiteral("blue", defaultTokenPosition()),
            ),
            ReAssignationNode(
                defaultTokenPosition(),
                NumberLiteralNode(
                    1,
                    defaultTokenPosition(),
                ),
                IdentifierNode("a", defaultTokenPosition()),
            ),
            DeclarationAssignation(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("aBlue", defaultTokenPosition()),
                StringLiteral("blue", defaultTokenPosition()),
            ),
        ),
    )

val input006 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            PrintLnNode(
                defaultTokenPosition(),
                SumNode(
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
            ),
            ReadInputNode(
                defaultTokenPosition(),
                IdentifierNode(
                    "Introduzca un valor",
                    defaultTokenPosition(),
                ),
            ),
        ),
    )

val input007 =
    DeclarationAssignation(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        StringNodeType,
        IdentifierNode("input", defaultTokenPosition()),
        ReadEnvNode(
            defaultTokenPosition(),
            "PATH",
        ),
    )

val input008 =
    DeclarationAssignation(
        LetKeywordNode(defaultTokenPosition()),
        defaultTokenPosition(),
        BooleanNodeType,
        IdentifierNode("b", defaultTokenPosition()),
        BooleanLiteralNode(true, defaultTokenPosition()),
    )

val input009 =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("a", defaultTokenPosition()),
                ReadEnvNode(
                    defaultTokenPosition(),
                    "PATH",
                ),
            ),
            DeclarationAssignation(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                BooleanNodeType,
                IdentifierNode("b", defaultTokenPosition()),
                BooleanLiteralNode(true, defaultTokenPosition()),
            ),
        ),
    )

val input009_reversed =
    ProgramNode(
        defaultTokenPosition(),
        listOf(
            DeclarationAssignation(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                BooleanNodeType,
                IdentifierNode("b", defaultTokenPosition()),
                BooleanLiteralNode(true, defaultTokenPosition()),
            ),
            DeclarationAssignation(
                LetKeywordNode(defaultTokenPosition()),
                defaultTokenPosition(),
                StringNodeType,
                IdentifierNode("a", defaultTokenPosition()),
                ReadEnvNode(
                    defaultTokenPosition(),
                    "PATH",
                ),
            ),
        ),
    )
