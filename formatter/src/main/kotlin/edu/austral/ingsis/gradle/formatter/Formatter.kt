package edu.austral.ingsis.gradle.formatter

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.AssignationNode
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.ast.OperatorNode
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.TypeNode
import edu.austral.ingsis.gradle.formatter.util.Rule
import edu.austral.ingsis.gradle.formatter.util.RuleApplier
import edu.austral.ingsis.gradle.formatter.util.findIdentifierOrNumberOrStringOrOperatorNode
import edu.austral.ingsis.gradle.formatter.util.findNode

class Formatter(private val rules: List<Rule>) {
    fun format(node: ASTNode): String {
        val formatted = formatNode(node).joinToString("\n")
        val ruleApplier = RuleApplier()
        return ruleApplier.applyRules(formatted, rules)
    }

    private fun formatNode(node: ASTNode): List<String> {
        return node.children.map { formatChild(it) }
    }

    private fun formatChild(node: ASTNode): String {
        return when (node.nodeType) {
            is AssignationNode -> formatAssignationNode(node)
            is PrintLnNode -> formatPrintLnNode(node)
            is OperatorNode -> findIdentifierOrNumberOrStringOrOperatorNode(listOf(node))
            is IdentifierNode -> formatInitialization(node)
            else -> throw Exception("Invalid node type")
        } + ";"
    }

    // let
    private fun formatAssignationNode(node: ASTNode): String {
        if (findNode(node, KeywordNode) == null) {
            return formatReassignment(node)
        }
        return formatDeclaration(node)
    }

    private fun formatReassignment(node: ASTNode): String {
        val result = ""
        result.plus(findNode(node, IdentifierNode)?.value)
            .plus("=")
            .plus(findIdentifierOrNumberOrStringOrOperatorNode(node.children))
        return result
    }

    private fun formatDeclaration(node: ASTNode): String {
        val identifier = findNode(node, IdentifierNode)?.value
        val type = findNode(node, TypeNode)?.value
        val value = findIdentifierOrNumberOrStringOrOperatorNode(node.children)
        return "let".plus(" ").plus(identifier).plus(" : ").plus(type).plus(" = ").plus(value)
    }

    private fun formatPrintLnNode(node: ASTNode): String {
        return "println".plus(findIdentifierOrNumberOrStringOrOperatorNode(node.children))
    }

    private fun formatInitialization(node: ASTNode): String {
        return "let".plus(findNode(node, IdentifierNode)?.value)
            .plus(" : ")
            .plus(findNode(node, TypeNode)?.value)
    }
}
