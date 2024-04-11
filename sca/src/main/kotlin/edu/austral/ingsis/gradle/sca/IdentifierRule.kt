package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.newast.Declaration
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.Function
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.Operand
import edu.austral.ingsis.gradle.common.ast.newast.Operator
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.Statement
import edu.austral.ingsis.gradle.common.token.TokenPosition
import edu.austral.ingsis.gradle.sca.util.IdentifierRuleType
import edu.austral.ingsis.gradle.sca.util.generateReport

class IdentifierRule(
    private val identifierRuleType: IdentifierRuleType,
    private val ruleTypeErrorMessageMap: Map<IdentifierRuleType, ReportErrorMessage>,
) :
    Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return when (node) {
            is ProgramNode -> verifyChildren(node.children)
            else -> ReportSuccess
        }
    }

    private fun verifyChildren(nodes: List<AST>): ReportResult {
        if (nodes.isEmpty()) return ReportSuccess
        val reports =
            nodes.flatMap { node ->
                when (node) {
                    is Statement -> analyzeStatement(node)
                    else -> listOf(ReportSuccess)
                }
            }
        return generateReport(reports)
    }

    private fun applyRegex(
        value: String,
        tokenPosition: TokenPosition,
    ): ReportResult {
        return if (value.matches(identifierRuleType.regex)) ReportSuccess else ReportFailure(listOf(getErrorMessage(tokenPosition)))
    }

    private fun analyzeStatement(node: Statement): List<ReportResult> {
        return when (node) {
            is Declaration -> listOf(applyRegex(node.identifierNode.name, node.tokenPosition))
            is Function -> analyzeFunctionCase(node)
            is Operand -> analyzeExpression(node)
            is ControlStatement -> analyzeControlStatement(node)
            else -> listOf(ReportSuccess) // 3
        }
    }

    private fun analyzeFunctionCase(node: Function): List<ReportResult> {
        return when (node) {
            is PrintLnNode -> analyzeExpression(node.expression)
            else -> listOf(ReportSuccess)
        }
    }

    private fun analyzeExpression(node: Expression): List<ReportResult> {
        return when (node) {
            is Operand -> listOf(analyzeIdentifier(node))
            is Operator -> {
                val leftResults = analyzeExpression(node.left)
                val rightResults = analyzeExpression(node.right)
                val combinedResults = mutableListOf<ReportResult>()
                combinedResults.addAll(leftResults)
                combinedResults.addAll(rightResults)
                if (combinedResults.all { it == ReportSuccess }) {
                    listOf(ReportSuccess)
                } else {
                    combinedResults.filter { it != ReportSuccess }
                }
            }
            else -> listOf(ReportSuccess)
        }
    }

    private fun analyzeIdentifier(node: Operand): ReportResult {
        return when (node) {
            is IdentifierNode -> applyRegex(node.name, node.tokenPosition)
            else -> ReportSuccess
        }
    }

    private fun analyzeControlStatement(node: ControlStatement): List<ReportResult> {
        when (node) {
            is IfStatement -> {
                val conditionResult = analyzeExpression(node.condition)
                val blockResult = analyzeBlock(node.ifBlock)
                return conditionResult + blockResult
            }
            is IfElseStatement -> {
                val conditionResult = analyzeExpression(node.condition)
                val blockResult = analyzeBlock(node.ifBlock)
                val elseBlockResult = analyzeBlock(node.elseBlock)
                return conditionResult + blockResult + elseBlockResult
            }
            else -> return listOf(ReportSuccess)
        }
    }

    private fun analyzeBlock(node: BlockNode): List<ReportResult> {
        return node.statements.flatMap { statement -> analyzeStatement(statement) }
    }

    private fun getErrorMessage(tokenPosition: TokenPosition): String {
        return ruleTypeErrorMessageMap[identifierRuleType]?.build(tokenPosition) ?: "Invalid identifier"
    }
}
