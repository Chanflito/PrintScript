package edu.austral.ingsis.gradle.formatter.outdated

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.formatter.rule.ComposeRule
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleAdapter
import edu.austral.ingsis.gradle.formatter.rule.adapter.RuleData
import edu.austral.ingsis.gradle.formatter.util.findIdentifierOrNumberOrStringOrOperatorNode
import edu.austral.ingsis.gradle.formatter.util.findNode

class Formatter {
    fun format(
        node: ASTNode,
        ruleData: List<RuleData>,
    ): String {
        val formatted = formatNode(node).joinToString("\n")
        val adaptedRules = ruleData.map { RuleAdapter().adapt(it) }
        val composedRule = ComposeRule(adaptedRules)
        return composedRule.applyRule(formatted)
    }

    private fun formatNode(node: ASTNode): List<String> {
        return node.children.map { formatChild(it) }
    }

    private fun formatChild(node: ASTNode): String {
        return when (node.nodeType) {
            is AssignationNode -> formatAssignmentNode(node)
            is PrintLnNode -> formatPrintLnNode(node)
            is OperatorNode -> findIdentifierOrNumberOrStringOrOperatorNode(listOf(node))
            is IdentifierNode -> formatInitialization(node)
            else -> throw Exception("Invalid node type")
        } + ";"
    }

    private fun formatAssignmentNode(node: ASTNode): String {
        if (findNode(node, KeywordNode) == null) {
            return formatReassignment(node)
        }
        return formatDeclaration(node)
    }

    private fun formatReassignment(node: ASTNode): String {
        val identifier = findNode(node, IdentifierNode)?.value
        val value = findIdentifierOrNumberOrStringOrOperatorNode(node.children)
        return "$identifier=$value"
    }

    private fun formatDeclaration(node: ASTNode): String {
        val identifier = findNode(node, IdentifierNode)?.value ?: ""
        val type = findNode(node, TypeNode)?.value ?: ""
        val value = findIdentifierOrNumberOrStringOrOperatorNode(node.children)
        return "let $identifier:$type=$value"
    }

    private fun formatPrintLnNode(node: ASTNode): String {
        return "println(${findIdentifierOrNumberOrStringOrOperatorNode(node.children)})"
    }

    private fun formatInitialization(node: ASTNode): String {
        val identifier = findNode(node, IdentifierNode)?.value ?: ""
        val type = findNode(node, TypeNode)?.value ?: ""
        return "let $identifier:$type"
    }
}
