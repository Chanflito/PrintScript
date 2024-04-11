package edu.austral.ingsis.gradle.sca

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.common.ast.newast.IfStatement
import edu.austral.ingsis.gradle.common.ast.newast.Operand
import edu.austral.ingsis.gradle.common.ast.newast.Operator
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.Statement
import edu.austral.ingsis.gradle.sca.util.generateReport

class ReadInputRule : Rule<AST> {
    override fun verify(node: AST): ReportResult {
        return when (node) {
            is ProgramNode -> verifyChildren(node.children)
            else -> ReportSuccess
        }
    }

    private fun verifyChildren(nodes: List<Statement>): ReportResult {
        if (nodes.isEmpty()) return ReportSuccess
        val reports =
            nodes.flatMap { node ->
                when (node) {
                    is ReadInputNode -> listOf(verifyExpression(node.expression))
                    is ControlStatement -> verifyControlStatement(node)
                    else -> listOf(ReportSuccess)
                }
            }
        return generateReport(reports)
    }

    private fun verifyExpression(expression: Expression): ReportResult {
        return when (expression) {
            is Operator, is ReadEnvNode, is ReadInputNode ->
                ReportFailure(listOf(PrintlnReportErrorMessage().build(expression.tokenPosition)))
            is Operand -> ReportSuccess
        }
    }

    private fun verifyControlStatement(statement: ControlStatement): List<ReportResult> {
        return when (statement) {
            is IfStatement -> {
                val blockResult = searchReadInput(statement.ifBlock)
                blockResult
            }
            is IfElseStatement -> {
                val ifBlock = searchReadInput(statement.ifBlock)
                val elseBlock = searchReadInput(statement.elseBlock)
                ifBlock + elseBlock
            }
            else -> listOf(ReportSuccess)
        }
    }

    private fun searchReadInput(node: BlockNode): List<ReportResult> {
        return node.statements.map { statement ->
            when (statement) {
                is ReadInputNode -> verifyExpression(statement.expression)
                else -> ReportSuccess
            }
        }
    }
}
