package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.DeclarationNode
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult

class DeclarationInterpreter(val node: AST, val context: Context): Interpreter{
    override fun interpret(): InterpretResult {
        val declarationNode = node as DeclarationNode
        interpretNode(declarationNode)
        return InterpretResult.ContextResult(context)
    }


    private fun interpretNode(node: DeclarationNode) {
        if(context.isInContext(node.identifierNode.name)){
            throw RuntimeException("Variable ${node.identifierNode.name} already declared")
        }
        when(node.keyword.value){
            "let" -> {
                context.declareVariable(node.identifierNode.name, node.nodeType)
            }
            "const" -> {
                throw RuntimeException("Cannot declare a constant without initialization")
            }
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is DeclarationNode
    }

}
