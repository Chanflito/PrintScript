package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.Type

class Context {
    private val assignedVariables = mutableMapOf<String, Any>()
    private val constants= mutableMapOf<String, Any>()
    private val declaredVariables = mutableMapOf<String, Type>()
    private val binaryOperationsResults= mutableListOf(Any())
    private val printValues = mutableListOf<String>()


    fun assignVariable(name: String, value: Any) {
        assignedVariables[name] = value
    }

    fun assignConstant(name: String, value: Any) {
        constants[name] = value
    }

    fun declareVariable(name: String, type: Type) {
        declaredVariables[name] = type
    }

    fun getVariable(name: String): Any? {
        return assignedVariables[name]
    }

    fun getConstant(name: String): Any? {
        return constants[name]
    }

    fun getVariableType(name: String): Type? {
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

    fun getBinaryOperationsResults(): List<Any> {
        return binaryOperationsResults
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

    fun addBinaryOperationResult(result: Any) {
        binaryOperationsResults.add(result)
    }

    fun addPrintValue(value: String) {
        printValues.add(value)
    }

    fun getLastBinaryOperationResult(): Any {
        return binaryOperationsResults.last()
    }
}