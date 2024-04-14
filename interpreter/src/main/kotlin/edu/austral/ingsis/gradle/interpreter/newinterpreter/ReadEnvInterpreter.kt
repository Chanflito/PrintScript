package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.OperationResult

import kotlin.RuntimeException

class ReadEnvInterpreter(val node: AST, val context: Context, val type: NodeType): Interpreter {

    override fun interpret(): InterpretResult {
        val readEnvNode = node as ReadEnvNode
        return InterpretResult.InterpretOperationResult(interpretNode(readEnvNode))
    }

    private fun interpretNode(node: ReadEnvNode): OperationResult {
        val envVar = node.value
        val envValue = System.getenv(envVar) ?: ""

        return when (type) {
            is StringNodeType -> interpretStringNode(envValue)
            is NumberNodeType -> interpretNumberNode(envValue, envVar)
            is BooleanNodeType -> interpretBooleanNode(envValue, envVar)
            else -> throw RuntimeException("Type not recognized")
        }
    }

    private fun interpretStringNode(envValue: String): OperationResult.StringResult {
        return OperationResult.StringResult(envValue)
    }

    private fun interpretNumberNode(envValue: String, envVar: String): OperationResult.NumberResult {
        val numberValue = envValue.toDoubleOrNull()
        return if (numberValue != null) {
            OperationResult.NumberResult(numberValue)
        } else {
            throw RuntimeException("Conversion to Number failed for environment variable: $envVar")
        }
    }

    private fun interpretBooleanNode(envValue: String, envVar: String): OperationResult.BooleanResult {
        val booleanValue = envValue.toBooleanStrictOrNull()
        return if (booleanValue != null) {
            OperationResult.BooleanResult(booleanValue)
        } else {
            throw RuntimeException("Conversion to Boolean failed for environment variable: $envVar")
        }
    }


    override fun canInterpret(node: AST): Boolean {
        return node is ReadEnvNode
    }
}
