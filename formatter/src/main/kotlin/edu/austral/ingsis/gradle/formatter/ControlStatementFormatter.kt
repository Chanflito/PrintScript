package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.IfElseStatementNode
import edu.austral.ingsis.gradle.common.ast.IfStatementNode
import edu.austral.ingsis.gradle.formatter.rule.Rules

class IfStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is IfStatementNode) throw IllegalArgumentException("$node is not a IfStatement")

        val composeFormatter = createDefaultFormatter()
        val condition = composeFormatter.format(node.condition, rules)
        val block = composeFormatter.format(node.ifBlock, rules)
        val result = "if ($condition) {\n${block}\n}"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfStatementNode
    }
}

class IfElseStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        rules: Rules,
    ): String {
        if (node !is IfElseStatementNode) throw IllegalArgumentException("$node is not a IfElseStatement")

        val composeFormatter = createDefaultFormatter()
        val condition = composeFormatter.format(node.condition, rules)
        val ifBlock = composeFormatter.format(node.ifBlock, rules)
        val elseBlock = composeFormatter.format(node.elseBlock, rules)
        val result = "if ($condition) {\n${ifBlock}\n}else {\n${elseBlock}\n}"
        return applyFormat(result, rules.defaultRule)
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfElseStatementNode
    }
}
