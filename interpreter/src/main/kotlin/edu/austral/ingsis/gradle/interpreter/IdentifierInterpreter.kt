package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

/**
 * Interpreter resolving identifiers and returning their corresponding value
 */
class IdentifierInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as IdentifierNode

        val identifier = node.name
        if (!context.isInContext(identifier)) {
            throw RuntimeException(
                "Variable $identifier not declared",
            )
        }
        return InterpretResult.OperationResult(context.getVariableOrConstant(identifier)!!)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is IdentifierNode
    }
}
