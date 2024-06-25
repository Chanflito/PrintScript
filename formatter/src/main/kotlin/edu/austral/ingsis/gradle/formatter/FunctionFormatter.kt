package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class PrintLnFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is PrintLnNode) throw IllegalArgumentException("$node is not a PrintLnNode")

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
        if (node !is ReadEnvNode) throw IllegalArgumentException("$node is not a ReadEnvNode")

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
        if (node !is ReadInputNode) throw IllegalArgumentException("$node is not a ReadInputNode")

        val composeFormatter = createDefaultFormatter()
        val value = composeFormatter.format(node.expression, rules)
        val result = "readInput($value)"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is ReadInputNode
    }
}
