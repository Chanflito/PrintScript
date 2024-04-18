package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class PrintLnFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rule: Rule,
        ifBlockRules: Rule,
    ): String {
        return when (node) {
            is PrintLnNode -> {
                val value = ExpressionFormatter().format(node.expression, rule, ifBlockRules)
                val result = "println($value);"
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

    override fun canFormat(node: AST): Boolean {
        return node is PrintLnNode
    }
}
