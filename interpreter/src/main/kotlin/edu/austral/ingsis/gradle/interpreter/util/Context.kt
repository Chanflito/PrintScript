package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.NodeType

class Context {
    private val assignedVariables = mutableMapOf<String, OperationResult>()
    private val constants= mutableMapOf<String, OperationResult>()
    private val declaredVariables = mutableMapOf<String, NodeType>()
    private val printValues = mutableListOf<String>()


    fun assignVariable(name: String, value: OperationResult) {
        assignedVariables[name] = value
    }


    fun assignConstant(name: String, value: OperationResult) {
        constants[name] = value
    }

    fun declareVariable(name: String, type: NodeType) {
        declaredVariables[name] = type
    }

    fun getVariable(name: String): OperationResult? {
        return assignedVariables[name]
    }

    fun getConstant(name: String): OperationResult? {
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

    fun addPrintValue(value: String) {
        printValues.add(value)
    }


    fun getVariableOrConstant(name: String): OperationResult? {
        return assignedVariables[name] ?: constants[name]
    }

    fun update(other: Context): Context {
        val updatedContext = Context()
        updatedContext.assignedVariables.putAll(this.assignedVariables)
        updatedContext.constants.putAll(this.constants)
        updatedContext.declaredVariables.putAll(this.declaredVariables)
        updatedContext.printValues.addAll(this.printValues)

        updatedContext.assignedVariables.putAll(other.assignedVariables)
        updatedContext.constants.putAll(other.constants)
        updatedContext.declaredVariables.putAll(other.declaredVariables)
        updatedContext.printValues.addAll(other.printValues)

        return updatedContext
    }
}