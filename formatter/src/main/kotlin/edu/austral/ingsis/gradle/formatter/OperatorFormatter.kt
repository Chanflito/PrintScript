package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DivideNode
import edu.austral.ingsis.gradle.common.ast.newast.MultiplyNode
import edu.austral.ingsis.gradle.common.ast.newast.SubtractNode
import edu.austral.ingsis.gradle.common.ast.newast.SumNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class SumFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is SumNode -> {
                val composeFormatter = createDefaultFormatter()
                val result = "${composeFormatter.format(
                    node.left,
                    defaultRule,
                    ifBlockRule,
                )} + ${composeFormatter.format(node.right, defaultRule, ifBlockRule)}"
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

    override fun canFormat(node: AST): Boolean {
        return node is SumNode
    }
}

class SubtractFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is SubtractNode -> {
                val composeFormatter = createDefaultFormatter()
                val result = "${composeFormatter.format(
                    node.left,
                    defaultRule,
                    ifBlockRule,
                )} - ${composeFormatter.format(node.right, defaultRule,ifBlockRule)}"
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

    override fun canFormat(node: AST): Boolean {
        return node is SubtractNode
    }
}

class MultiplyFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is MultiplyNode -> {
                val composeFormatter = createDefaultFormatter()
                val result = "${composeFormatter.format(
                    node.left,
                    defaultRule,
                    ifBlockRule,
                )} * ${composeFormatter.format(node.right, defaultRule, ifBlockRule)}"
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

    override fun canFormat(node: AST): Boolean {
        return node is MultiplyNode
    }
}

class DivideFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        ifBlockRule: Rule,
    ): String {
        return when (node) {
            is DivideNode -> {
                val composeFormatter = createDefaultFormatter()
                val result = "${composeFormatter.format(
                    node.left,
                    defaultRule,
                    ifBlockRule,
                )} / ${composeFormatter.format(node.right, defaultRule, ifBlockRule)}"
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

    override fun canFormat(node: AST): Boolean {
        return node is DivideNode
    }
}
