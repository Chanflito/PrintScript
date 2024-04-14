package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationAssignation
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.doesTypeMatch

class DeclarationAssignationInterpreter (val node: AST, val context: Context): Interpreter {


    override fun interpret(): InterpretResult {
        val declarationNode = node as DeclarationAssignation
        interpretNode(declarationNode)
        return InterpretResult.ContextResult(context)
    }
    private fun interpretNode(node:DeclarationAssignation){
        val identifier = node.identifierNode.name
        val expression = node.expression
        val keyword = node.keyword.value
        val type = node.nodeType
        if (context.isInContext(identifier)) {
            throw RuntimeException(
                "Variable $identifier already declared",
            )
        }
        val expressionInterpreter = InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(expression, context, type)
        val interpretResult = expressionInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val result= interpretResult.operationResult
        if (!doesTypeMatch(result, type)) throw RuntimeException("Type mismatch")
        when (keyword) {
            "let" -> {
                context.assignVariable(identifier, result)
            }
            "const" -> {
                context.assignConstant(identifier, result)
            }
        }
        context.declareVariable(identifier, type)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DeclarationAssignation
    }

}