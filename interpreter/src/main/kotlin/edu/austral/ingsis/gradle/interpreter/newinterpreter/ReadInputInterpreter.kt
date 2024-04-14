package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.ReadInputNode
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.OperationResult

class ReadInputInterpreter(val node: AST, val context: Context, val type: NodeType): Interpreter {

    override fun interpret(): InterpretResult {
        val readInputNode = node as ReadInputNode
        return InterpretResult.InterpretOperationResult(readInput(readInputNode))
    }

    private fun readInput(node: ReadInputNode): OperationResult {
        val messageInterpreter= InterpreterFactory.internalGetInstance().createInterpreter<Interpreter>(node.expression, context, type)
        val messageOperationResult= messageInterpreter.interpret() as InterpretResult.InterpretOperationResult
        val message = messageOperationResult.operationResult.printValue()

        val inputValue = readInputValue(message)


        return when (type) {
            is StringNodeType -> OperationResult.StringResult(inputValue)
            is NumberNodeType -> interpretNumberInput(inputValue, message)
            is BooleanNodeType -> interpretBooleanInput(inputValue, message )
            else -> throw RuntimeException("Type not recognized")
        }
    }

    private fun readInputValue(prompt: String): String {
        println(prompt)
        return readLine() ?: ""
    }

    private fun interpretNumberInput(inputValue: String, prompt: String): OperationResult.NumberResult {
        val numberValue = inputValue.toDoubleOrNull()
        return if (numberValue != null) {
            OperationResult.NumberResult(numberValue)
        } else {
            throw RuntimeException("Conversion to Number failed for input: $prompt")
        }
    }

    private fun interpretBooleanInput(inputValue: String, prompt: String): OperationResult.BooleanResult {
        val booleanValue = inputValue.toBooleanStrictOrNull()
        return if (booleanValue != null) {
            OperationResult.BooleanResult(booleanValue)
        } else {
            throw RuntimeException("Conversion to Boolean failed for input: $prompt")
        }
    }

    override fun canInterpret(node: AST): Boolean {
        return node is ReadInputNode
    }



}