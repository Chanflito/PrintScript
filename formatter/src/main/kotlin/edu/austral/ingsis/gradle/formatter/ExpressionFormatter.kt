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
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ExpressionFormatter : Formatter<Expression> {
    override fun format(
        node: Expression,
        rule: Rule,
        ifBlockRules: Rule,
    ): String {
        return when (node) {
            // Operator
            is SumNode -> {
                val result = "${format(node.left, rule, ifBlockRules)} + ${format(node.right, rule, ifBlockRules)}"
                return applyFormat(result, rule)
            }

            is SubtractNode -> {
                val result = "${format(node.left, rule,ifBlockRules)} - ${format(node.right, rule,ifBlockRules)}"
                return applyFormat(result, rule)
            }

            is MultiplyNode -> {
                val result = "${format(node.left, rule, ifBlockRules)} * ${format(node.right, rule, ifBlockRules)}"
                return applyFormat(result, rule)
            }

            is DivideNode -> {
                val result = "${format(node.left, rule, ifBlockRules)} / ${format(node.right, rule, ifBlockRules)}"
                return applyFormat(result, rule)
            }

            // Operand
            is IdentifierNode -> return IdentifierFormatter().format(node, rule, ifBlockRules)

            // Literal
            is StringLiteral -> {
                val result = "\"${node.value}\""
                return applyFormat(result, rule)
            }

            is NumberLiteralNode -> {
                val result = node.value.toString()
                return applyFormat(result, rule)
            }

            is BooleanLiteralNode -> {
                val result = node.value.toString()
                return applyFormat(result, rule)
            }

            else -> ""
        }
    }

    override fun applyFormat(
        result: String,
        rule: Rule,
    ): String {
        return rule.applyRule(result)
    }

    // TODO - split into multiple formatters
    override fun canFormat(node: Expression): Boolean {
        return node is SumNode ||
            node is SubtractNode ||
            node is MultiplyNode ||
            node is DivideNode ||
            node is IdentifierNode ||
            node is StringLiteral ||
            node is NumberLiteralNode ||
            node is BooleanLiteralNode
    }
}
