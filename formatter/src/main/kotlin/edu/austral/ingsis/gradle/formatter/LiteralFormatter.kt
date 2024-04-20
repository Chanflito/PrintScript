package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.formatter.rule.Rule

class StringFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is StringLiteral -> {
                val result = node.value
                return applyFormat(result, defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is StringLiteral
    }
}

class NumberFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is NumberLiteralNode -> {
                val result = node.value.toString()
                return applyFormat(result, defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is NumberLiteralNode
    }
}

class BooleanFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is BooleanLiteralNode -> {
                val result = node.value.toString()
                return applyFormat(result, defaultRule)
            }
            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is BooleanLiteralNode
    }
}
