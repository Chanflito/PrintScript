package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class PrintLnFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        node as PrintLnNode

        val composeFormatter = createDefaultFormatter()
        val value = composeFormatter.format(node.expression, rules)
        val result = "println($value);"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is PrintLnNode
    }
}

class ReadEnvFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        node as ReadEnvNode

        val result = "readEnv(\"${node.value}\")"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReadEnvNode
    }
}

class ReadInputFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        node as ReadInputNode

        val composeFormatter = createDefaultFormatter()
        val value = composeFormatter.format(node.expression, rules)
        val result = "readInput(\"$value\");"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReadInputNode
    }
}
