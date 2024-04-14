package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult

class IdentifierInterpreter (val node: AST, val context: Context) : Interpreter {
    override fun interpret(): InterpretResult {
        val identifierNode = node as IdentifierNode
        val identifier = identifierNode.name
        if (!context.isInContext(identifier)) {
            throw RuntimeException(
                "Variable $identifier not declared",
            )
        }
        return InterpretResult.InterpretOperationResult(context.getVariableOrConstant(identifier)!!)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IdentifierNode
    }
}