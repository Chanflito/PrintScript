package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IdentifierNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class IdentifierInterpreter () : Interpreter {
    override fun interpret(node: AST, context: Context, interpreterManager: InterpreterManager): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val identifierNode = node as IdentifierNode
        val identifier = identifierNode.name
        if (!context.isInContext(identifier)) {
            throw RuntimeException(
                "Variable $identifier not declared",
            )
        }
        return InterpretResult.OperationResult(context.getVariable(identifier)!!)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IdentifierNode
    }
}