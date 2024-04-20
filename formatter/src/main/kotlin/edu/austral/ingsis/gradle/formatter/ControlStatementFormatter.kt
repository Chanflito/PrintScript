package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.formatter.rule.Rule

class IfStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is IfStatement -> {
                val composeFormatter = createDefaultFormatter()
                val condition = composeFormatter.format(node.condition, defaultRule, blockRule)
                val block = composeFormatter.format(node.ifBlock, defaultRule, blockRule)
                val result = "if ($condition) {\n${block}\n}"
                return applyFormat(result, defaultRule)
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfStatement
    }
}

class IfElseStatementFormatter : Formatter<AST> {
    override fun format(
        node: AST,
        defaultRule: Rule,
        blockRule: Rule,
    ): String {
        return when (node) {
            is IfElseStatement -> {
                val composeFormatter = createDefaultFormatter()
                val condition = composeFormatter.format(node.condition, defaultRule, blockRule)
                val ifBlock = composeFormatter.format(node.ifBlock, defaultRule, blockRule)
                val elseBlock = composeFormatter.format(node.elseBlock, defaultRule, blockRule)
                val result = "if ($condition) {\n${ifBlock}\n}else {\n${elseBlock}\n}"
                return applyFormat(result, defaultRule)
            }

            else -> ""
        }
    }

    override fun canFormat(node: AST): Boolean {
        return node is IfElseStatement
    }
}
