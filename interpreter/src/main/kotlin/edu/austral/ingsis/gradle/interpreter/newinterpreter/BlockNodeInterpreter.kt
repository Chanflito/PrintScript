package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.common.ast.newast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.interpreter.util.Context

class BlockNodeInterpreter: Interpreter<BlockNode> {

    private val expressionInterpreter = ExpressionInterpreter()
    private val reAssignationInterpreter = ReassignationInterpreter()
    private val printLnInterpreter = PrintLnInterpreter()
    private val declarationAssignationInterpreter = DeclarationAssignationInterpreter()
    private val declarationInterpreter = DeclarationInterpreter()
    private val controlStatementInterpreter = ControlStatementInterpreter()

    override fun interpret(node: BlockNode, context: Context): Context {
        var blockContext = context
        node.statements.forEach {
            when {
                it is Expression ->
                    blockContext = expressionInterpreter.interpret(it, context)
                it is ReAssignationNode ->
                    blockContext = reAssignationInterpreter.interpret(it, context)
                it is PrintLnNode -> {
                    blockContext = printLnInterpreter.interpret(it, context)
                }
                it is DeclarationAssignation -> {
                    blockContext = declarationAssignationInterpreter.interpret(it, context)
                }
                it is DeclarationNode -> {
                    blockContext = declarationInterpreter.interpret(it, context)
                }
                it is ControlStatement -> {
                    blockContext = controlStatementInterpreter.interpret(it, context)
                }
                else -> throw RuntimeException("Statement not found")
            }
        }
        return blockContext
    }

}