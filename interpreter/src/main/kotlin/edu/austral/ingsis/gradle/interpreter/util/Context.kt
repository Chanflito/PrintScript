package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.Literal
import edu.austral.ingsis.gradle.common.ast.newast.NodeType

class Context {
    private val assignedVariables = mutableMapOf<String, InterpreterResult>()
    private val constants= mutableMapOf<String, InterpreterResult>()
    private val declaredVariables = mutableMapOf<String, NodeType>()
    private val binaryOperationsResults: MutableList<InterpreterResult> = mutableListOf()
    private val printValues = mutableListOf<String>()


    fun assignVariable(name: String, value: InterpreterResult) {
        assignedVariables[name] = value
    }

    fun assignConstant(name: String, value: InterpreterResult) {
        constants[name] = value
    }

    fun declareVariable(name: String, type: NodeType) {
        declaredVariables[name] = type
    }

    fun getVariable(name: String): InterpreterResult? {
        return assignedVariables[name]
    }

    fun getConstant(name: String): InterpreterResult? {
        return constants[name]
    }

    fun getVariableType(name: String): NodeType? {
        return declaredVariables[name]
    }

    fun isVariableDeclared(name: String): Boolean {
        return declaredVariables.containsKey(name)
    }

    fun isVariableAssigned(name: String): Boolean {
        return assignedVariables.containsKey(name)
    }

    fun isConstantAssigned(name: String): Boolean {
        return constants.containsKey(name)
    }

    fun getPrintValues(): List<String> {
        return printValues
    }

    fun isVariableAssignedOrConstantAssigned(name: String): Boolean {
        return isVariableAssigned(name) || isConstantAssigned(name)
    }

    fun isInContext(name: String): Boolean {
        return isVariableDeclared(name) || isVariableAssigned(name) || isConstantAssigned(name)
    }

    fun addBinaryOperationResult(result: InterpreterResult) {
        binaryOperationsResults.add(result)
    }

    fun addPrintValue(value: String) {
        printValues.add(value)
    }

    fun getLastBinaryOperationResult(): InterpreterResult {
        return binaryOperationsResults.last()
    }

    fun getVariableOrConstant(name: String): InterpreterResult? {
        return assignedVariables[name] ?: constants[name]
    }
}