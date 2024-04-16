package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode

class ExpressionFormatter : Formatter<Expression> {
    override fun format(node: Expression): String {
        return when (node) {
            // Operator
            is SumNode -> "${format(node.left)} + ${format(node.right)}"
            is SubtractNode -> "${format(node.left)} - ${format(node.right)}"
            is MultiplyNode -> "${format(node.left)} * ${format(node.right)}"
            is DivideNode -> "${format(node.left)} / ${format(node.right)}"

            // Operand
            is IdentifierNode -> IdentifierFormatter().format(node)

            // Literal
            is StringLiteral -> return "\"${node.value}\""
            is NumberLiteralNode -> return node.value.toString()
            is BooleanLiteralNode -> return node.value.toString()

            else -> ""
        }
    }
}
