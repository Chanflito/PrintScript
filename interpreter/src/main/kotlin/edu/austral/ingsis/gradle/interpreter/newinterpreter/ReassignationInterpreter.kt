package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.ReAssignationNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class ReassignationInterpreter (val node: AST, val context: Context) : Interpreter {
    override fun interpret(): InterpretResult {
        val reassignationNode = node as ReAssignationNode
        interpretNode(reassignationNode, context)
        return InterpretResult.ContextResult(context)
    }

    private fun interpretNode(node: ReAssignationNode, context: Context) {
        val identifier = node.identifierNode.name
        val expression = node.expression
        val type = context.getVariableType(identifier)
        if (!context.isInContext(identifier)) {
            throw RuntimeException(
                "Variable $identifier not declared",
            )
        }
        if(context.isConstantAssigned(identifier)){
            throw RuntimeException(
                "Variable $identifier is constant",
            )
        }
        val expressionInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(expression, context)
        val interpretResult = expressionInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val result = interpretResult.operationResult
        if (!doesTypeMatch(result, type)) throw RuntimeException("Type mismatch")
        context.assignVariable(identifier, result)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is ReAssignationNode
    }

}
