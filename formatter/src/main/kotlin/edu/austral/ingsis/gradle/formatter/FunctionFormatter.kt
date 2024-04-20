package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.formatter.rule.Rule

class PrintLnFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is PrintLnNode -> {
                val composeFormatter = createDefaultFormatter()
                val value = composeFormatter.format(node.expression, defaultRule, blockRule)
                val result = "println($value);"
                return applyFormat(result, defaultRule)
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is PrintLnNode
    }
}

class ReadEnvFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is ReadEnvNode -> {
                val result = "readEnv(\"${node.value}\")"
                return applyFormat(result, defaultRule)
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReadEnvNode
    }
}

class ReadInputFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is ReadInputNode -> {
                val composeFormatter = createDefaultFormatter()
                val value = composeFormatter.format(node.expression, defaultRule, blockRule)
                val result = "readInput(\"$value\");"
                return applyFormat(result, defaultRule)
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReadInputNode
    }
}
