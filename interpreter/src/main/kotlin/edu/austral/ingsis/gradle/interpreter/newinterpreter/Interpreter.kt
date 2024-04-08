package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.ControlStatement
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.common.ast.newast.Expression
import edu.austral.ingsis.gradle.common.ast.newast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.newast.ProgramNode
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.common.ast.newast.Type

class Interpreter {
    var assignedVariables = HashMap<String, Any>()
    var printedElements = mutableListOf<String>()
    var results = mutableListOf<Any>()
    var constants = HashMap<String, Any>()
    var declaredVariables = HashMap<String, Type>()

    val expressionInterpreter = ExpressionInterpreter()
    val reAssignationInterpreter = ReassignationInterpreter()
    val printLnInterpreter = PrintLnInterpreter()
    val declarationAssignationInterpreter = DeclarationAssignationInterpreter()
    val declarationInterpreter = DeclarationInterpreter()
    val controlStatementInterpreter = ControlStatementInterpreter()

    fun interpret(program: ProgramNode) {
        program.children.forEach {
            when {
                it is Expression ->
                    expressionInterpreter.interpret(it, assignedVariables, constants)
                        ?.let { result -> results.add(result) }
                it is ReAssignationNode ->
                    assignedVariables =
                        reAssignationInterpreter.interpret(it, assignedVariables, constants, declaredVariables)
                it is PrintLnNode -> printedElements.add(printLnInterpreter.interpret(it, assignedVariables, constants))
                it is DeclarationAssignation -> {
                    val (newVariables, newConstants, newDeclaredVariables) =
                        declarationAssignationInterpreter.interpret(
                            it,
                            assignedVariables,
                            constants,
                            declaredVariables,
                        )
                    assignedVariables = newVariables
                    constants = newConstants
                    declaredVariables = newDeclaredVariables
                }
                it is DeclarationNode -> {
                    declaredVariables = declarationInterpreter.interpret(it, assignedVariables, constants, declaredVariables)
                }
                it is ControlStatement -> controlStatementInterpreter.interpret(it, assignedVariables, constants, declaredVariables)
                else -> throw RuntimeException("Statement not found")
            }
        }
    }
}
