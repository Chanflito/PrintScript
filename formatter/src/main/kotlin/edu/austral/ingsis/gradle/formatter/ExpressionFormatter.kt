package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class ExpressionFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            // Operator
            is SumNode -> {
                val result = "${format(node.left, defaultRule, ifBlockRule)} + ${format(node.right, defaultRule, ifBlockRule)}"
                return applyFormat(result, defaultRule)
            }

            is SubtractNode -> {
                val result = "${format(node.left, defaultRule,ifBlockRule)} - ${format(node.right, defaultRule,ifBlockRule)}"
                return applyFormat(result, defaultRule)
            }

            is MultiplyNode -> {
                val result = "${format(node.left, defaultRule, ifBlockRule)} * ${format(node.right, defaultRule, ifBlockRule)}"
                return applyFormat(result, defaultRule)
            }

            is DivideNode -> {
                val result = "${format(node.left, defaultRule, ifBlockRule)} / ${format(node.right, defaultRule, ifBlockRule)}"
                return applyFormat(result, defaultRule)
            }

            // Operand
            is IdentifierNode -> return IdentifierFormatter().format(node, defaultRule, ifBlockRule)

            // Literal
            is StringLiteral -> {
                val result = "\"${node.value}\""
                return applyFormat(result, defaultRule)
            }

            is NumberLiteralNode -> {
                val result = node.value.toString()
                return applyFormat(result, defaultRule)
            }

            is BooleanLiteralNode -> {
                val result = node.value.toString()
                return applyFormat(result, defaultRule)
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
    override fun canFormat(node: AST): Boolean {
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
