package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class DeclarationInterpreter() : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val declarationNode = node as DeclarationNode
        if (context.isInContext(declarationNode.identifierNode.name)) {
            throw RuntimeException("Variable ${declarationNode.identifierNode.name} already declared")
        }
        return when (declarationNode.keyword.value) {
            "let" -> {
                val newContext =
                    Context(declaredVariablesAndConstants = mapOf(declarationNode.identifierNode.name to declarationNode.nodeType))
                InterpretResult.ContextResult(newContext)
            }
            "const" -> {
                throw RuntimeException("Cannot declare a constant without initialization")
            }
            else -> throw RuntimeException("Invalid keyword: ${declarationNode.keyword.value}")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DeclarationNode
    }
}
