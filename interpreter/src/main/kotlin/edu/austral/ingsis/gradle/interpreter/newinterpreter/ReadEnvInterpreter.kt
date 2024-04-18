package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.EnvReader
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import kotlin.RuntimeException

class ReadEnvInterpreter(val type: NodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        val readEnvNode = node as ReadEnvNode
        val value = readEnvNode.value
        return when (type) {
            is NumberNodeType -> {
                val number = convertToNumber(value, interpreterManager.envReader)
                InterpretResult.OperationResult(number)
            }
            is StringNodeType -> {
                val stringValue = convertToString(value, interpreterManager.envReader)
                InterpretResult.OperationResult(stringValue)
            }
            is BooleanNodeType -> {
                val booleanValue = convertToBoolean(value, interpreterManager.envReader)
                InterpretResult.OperationResult(booleanValue)
            }
            else -> throw RuntimeException("Cannot interpret node $node")
        }
    }

    private fun convertToNumber(
        value: String,
        envReader: EnvReader,
    ): Int {
        return when (val valueFromEnv = envReader.readEnv(value)) {
            is String -> valueFromEnv.toIntOrNull()
            is Number -> valueFromEnv.toInt() // If it's already a number, just take the integer value
            else -> null
        } ?: throw RuntimeException("Cannot convert $value to number")
    }

    private fun convertToString(
        value: String,
        envReader: EnvReader,
    ): String {
        return envReader.readEnv(value).toString()
    }

    private fun convertToBoolean(
        value: String,
        envReader: EnvReader,
    ): Boolean {
        return when (val valueFromEnv = envReader.readEnv(value)) {
            is Boolean -> valueFromEnv
            is String -> valueFromEnv.toBooleanStrictOrNull() ?: throw RuntimeException("Cannot convert $value to boolean")
            else -> throw RuntimeException("Cannot convert $value to boolean")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is ReadEnvNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}
