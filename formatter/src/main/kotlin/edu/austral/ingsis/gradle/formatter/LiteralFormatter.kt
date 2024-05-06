package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.NumberLiteralNode
import edu.austral.ingsis.gradle.common.ast.newast.StringLiteral
import edu.austral.ingsis.gradle.formatter.rule.Rules

class StringFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is StringLiteral) throw IllegalArgumentException("$node is not a StringLiteral")

        val result = node.value
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is StringLiteral
    }
}

class NumberFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is NumberLiteralNode) throw IllegalArgumentException("$node is not a NumberLiteralNode")

        val result = node.value.toString()
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is NumberLiteralNode
    }
}

class BooleanFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is BooleanLiteralNode) throw IllegalArgumentException("$node is not a BooleanLiteralNode")

        val result = node.value.toString()
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is BooleanLiteralNode
    }
}
