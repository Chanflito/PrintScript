package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.interpreter.util.Context

class ProgramNodeInterpreter: Interpreter<ProgramNode> {

    val expressionInterpreter = ExpressionInterpreter()
    val reAssignationInterpreter = ReassignationInterpreter()
    val printLnInterpreter = PrintLnInterpreter()
    val declarationAssignationInterpreter = DeclarationAssignationInterpreter()
    val declarationInterpreter = DeclarationInterpreter()
    val controlStatementInterpreter = ControlStatementInterpreter()

    override fun interpret(node: ProgramNode, context: Context): Context {
        var programContext = context
        node.children.forEach {
            when {
                it is Expression ->
                    programContext = expressionInterpreter.interpret(it, context)
                it is ReAssignationNode ->
                    programContext = reAssignationInterpreter.interpret(it, context)
                it is PrintLnNode -> {
                    programContext = printLnInterpreter.interpret(it, context)
                }
                it is DeclarationAssignation -> {
                    programContext = declarationAssignationInterpreter.interpret(it, context)
                }
                it is DeclarationNode -> {
                    programContext = declarationInterpreter.interpret(it, context)
                }
                it is ControlStatement -> {
                    programContext = controlStatementInterpreter.interpret(it, context)
                }
                else -> throw RuntimeException("Statement not found")
            }
        }
        return programContext
    }

}
