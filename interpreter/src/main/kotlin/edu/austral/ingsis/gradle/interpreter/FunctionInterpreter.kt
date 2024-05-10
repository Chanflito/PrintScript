package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.NodeType
import edu.austral.ingsis.gradle.common.ast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.PrintLnNode
import edu.austral.ingsis.gradle.common.ast.ReadEnvNode
import edu.austral.ingsis.gradle.common.ast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.EnvReader
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class PrintLnInterpreter : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as PrintLnNode

        val expression = node.expression
        val interpreter = interpreterManager.getInterpreter(expression)
        val interpreterResult = interpreter.interpret(expression, context, interpreterManager) as InterpretResult.OperationResult
        val resultValue = interpreterResult.value
        interpreterManager.printer.print(resultValue.toString())
        return InterpretResult.ContextResult(Context())
    }

    override fun canInterpret(node: AST): Boolean {
        return node is PrintLnNode
    }
}

class ReadEnvInterpreter(private val type: NodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as ReadEnvNode

        val value = node.value
        return checkTypeAndAttemptConversion(value, interpreterManager, node)
    }

    /**
     * Checks type assigned to the returning env variable and tries to convert it
     */
    private fun checkTypeAndAttemptConversion(
        value: String,
        interpreterManager: InterpreterManager,
        node: AST,
    ): InterpretResult {
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

class ReadInputInterpreter(private val type: NodeType) : Interpreter {
    override fun interpret(
        node: AST,
        context: Context,
        interpreterManager: InterpreterManager,
    ): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")
        node as ReadInputNode

        val expression = node.expression
        val expressionInterpreter = interpreterManager.getWithTypeOrNot(expression, type)
        val expressionResult =
            expressionInterpreter.interpret(expression, context, interpreterManager) as InterpretResult.OperationResult
        val value = expressionResult.value
        interpreterManager.printer.print(value.toString())

        val input =
            when (type) {
                is StringNodeType -> {
                    val rawInput = interpreterManager.inputReader.read(value.toString()) // Read raw input
                    rawInput.toString() // Convert raw input to String
                }

                is NumberNodeType -> {
                    when (val rawInput = interpreterManager.inputReader.read(value.toString())) {
                        is Number -> rawInput // No conversion needed for Numbers
                        is String -> {
                            if (rawInput.contains(".")) {
                                (
                                    rawInput.toDoubleOrNull()
                                        ?: throw RuntimeException("Invalid number input")
                                )
                            } else {
                                (rawInput.toIntOrNull() ?: throw RuntimeException("Invalid number input"))
                            }
                        }

                        else -> throw RuntimeException("Invalid number input")
                    }
                }

                is BooleanNodeType -> {
                    when (val rawInput = interpreterManager.inputReader.read(value.toString())) { // Read raw input
                        is Boolean -> rawInput // If raw input is already boolean, use it as is
                        is String -> rawInput.toBooleanStrictOrNull() ?: throw RuntimeException("Invalid boolean input")
                        else -> throw RuntimeException("Invalid boolean input")
                    }
                }

                else -> throw RuntimeException("Unsupported type: $type")
            }

        return InterpretResult.OperationResult(input)
    }

    override fun canInterpret(node: AST): Boolean {
        return node is ReadInputNode
    }

    override fun getNodeType(): NodeType {
        return type
    }
}
