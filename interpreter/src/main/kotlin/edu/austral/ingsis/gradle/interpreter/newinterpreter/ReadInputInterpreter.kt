package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.EnvReader
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager

class ReadInputInterpreter(val type: NodeType): Interpreter {

    override fun interpret(node: AST, context: Context, interpreterManager: InterpreterManager): InterpretResult {
        if (!canInterpret(node)) throw RuntimeException("Cannot interpret node $node")

        val readInputNode = node as ReadInputNode
        val expression = readInputNode.expression
        val expressionInterpreter = interpreterManager.getInterpreter(expression, type)
        val expressionResult = expressionInterpreter.interpret(expression, context, interpreterManager) as InterpretResult.OperationResult
        val value = expressionResult.value
        interpreterManager.printer.print(value.toString())

        val input = when (type) {
            is StringNodeType -> {
                val rawInput = interpreterManager.inputReader.read() // Read raw input
                rawInput.toString() // Convert raw input to String
            }
            is NumberNodeType -> {
                when (val rawInput = interpreterManager.inputReader.read()) {
                    is Number -> rawInput // No conversion needed for Numbers
                    is String -> rawInput.toDoubleOrNull() ?: throw RuntimeException("Invalid number input")
                    else -> throw RuntimeException("Invalid number input")
                }
            }
            is BooleanNodeType -> {
                when (val rawInput = interpreterManager.inputReader.read()) { // Read raw input
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


}