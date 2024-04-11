package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.interpreter.util.Context

class DeclarationInterpreter: Interpreter<DeclarationNode>{
    override fun interpret(
        node: DeclarationNode, context: Context): Context {
        val type = node.nodeType
        val name = node.identifierNode.name
        if (context.isInContext(name)) {
            throw RuntimeException("Variable $name already declared")
        }
        context.declareVariable(name, type)
        return context
    }
}
