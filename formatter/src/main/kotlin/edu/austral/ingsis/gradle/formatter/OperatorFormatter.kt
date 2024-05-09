package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.DivideNode
import edu.austral.ingsis.gradle.common.ast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.SumNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class SumFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is SumNode) throw IllegalArgumentException("$node is not a SumNode")

        val composeFormatter = createDefaultFormatter()
        val left = composeFormatter.format(node.left, rules)
        val right = composeFormatter.format(node.right, rules)
        val result = "$left + $right"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is SumNode
    }
}

class SubtractFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is SubtractNode) throw IllegalArgumentException("$node is not a SubtractNode")

        val composeFormatter = createDefaultFormatter()
        val left = composeFormatter.format(node.left, rules)
        val right = composeFormatter.format(node.right, rules)
        val result = "$left - $right"
        return applyFormat(result, rules.defaultRule)
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
        if (node !is MultiplyNode) throw IllegalArgumentException("$node is not a MultiplyNode")

        val composeFormatter = createDefaultFormatter()
        val left = composeFormatter.format(node.left, rules)
        val right = composeFormatter.format(node.right, rules)
        val result = "$left * $right"
        return applyFormat(result, rules.defaultRule)
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
        if (node !is DivideNode) throw IllegalArgumentException("$node is not a DivideNode")

        val composeFormatter = createDefaultFormatter()
        val left = composeFormatter.format(node.left, rules)
        val right = composeFormatter.format(node.right, rules)
        val result = "$left / $right"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is DivideNode
    }
}
