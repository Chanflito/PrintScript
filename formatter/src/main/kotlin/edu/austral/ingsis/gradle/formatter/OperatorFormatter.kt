package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class SumFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        return when (node) {
            is OperatorNode -> {
                val composeFormatter = createDefaultFormatter()
                val left = composeFormatter.format(node.left, rules)
                val right = composeFormatter.format(node.right, rules)
                val result = "$left + $right"
                return applyFormat(result, rules.defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is OperatorNode
    }
}

class SubtractFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        return when (node) {
            is SubtractNode -> {
                val composeFormatter = createDefaultFormatter()
                val left = composeFormatter.format(node.left, rules)
                val right = composeFormatter.format(node.right, rules)
                val result = "$left - $right"
                return applyFormat(result, rules.defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is SubtractNode
    }
}

class MultiplyFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        return when (node) {
            is MultiplyNode -> {
                val composeFormatter = createDefaultFormatter()
                val left = composeFormatter.format(node.left, rules)
                val right = composeFormatter.format(node.right, rules)
                val result = "$left * $right"
                return applyFormat(result, rules.defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is MultiplyNode
    }
}

class DivideFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        return when (node) {
            is DivideNode -> {
                val composeFormatter = createDefaultFormatter()
                val left = composeFormatter.format(node.left, rules)
                val right = composeFormatter.format(node.right, rules)
                val result = "$left / $right"
                return applyFormat(result, rules.defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is DivideNode
    }
}
