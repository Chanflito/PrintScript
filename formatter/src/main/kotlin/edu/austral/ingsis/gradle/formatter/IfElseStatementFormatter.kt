package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode

class IfElseStatementFormatter : Formatter<AST> {
    override fun format(node: AST): String {
        return when (node) {
            is IfElseStatement -> {
                val condition = ExpressionFormatter().format(node.condition)
                val ifBlock = formatIfBlock(node.ifBlock.children)
                val elseBlock = formatIfBlock(node.elseBlock.children)
                return "if ($condition) {\n${ifBlock}\n}else {\n${elseBlock}\n}"
            }

            else -> ""
        }
    }

    // este vendria a ser el caso inicial donde tengo una lista de nodos - visitor?
    // format()? crearComposeFormatter aca adentro?
    private fun formatIfBlock(nodes: List<AST>): String {
        val formattedDeclarations =
            nodes.map { child ->
                when (child) {
                    // Todas las posibilidades??
                    is DeclarationAssignation -> DeclarationAssignationFormatter().format(child)
                    is PrintLnNode -> PrintLnFormatter().format(child)
                    is ReadInputNode -> ReadInputFormatter().format(child)
                    is IdentifierNode -> IdentifierFormatter().format(child)

                    is SumNode -> "${ExpressionFormatter().format(child.left)} + ${ExpressionFormatter().format(child.right)}"
                    is SubtractNode -> "${ExpressionFormatter().format(child.left)} - ${
                        ExpressionFormatter().format(
                            child.right,
                        )
                    }"

                    is MultiplyNode -> "${ExpressionFormatter().format(child.left)} * ${
                        ExpressionFormatter().format(
                            child.right,
                        )
                    }"

                    is DivideNode -> "${ExpressionFormatter().format(child.left)} / ${ExpressionFormatter().format(child.right)}"

                    is StringLiteral -> child.value
                    is NumberLiteralNode -> child.value.toString()
                    is BooleanLiteralNode -> child.value.toString()

                    else -> ""
                }
            }
        return formattedDeclarations.joinToString("")
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfElseStatement
    }
}
