package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.NodeType

class Context(
    private val assignedVariables: Map<String, Any> = emptyMap(),
    private val constants: Map<String, Any> = emptyMap(),
    private val declaredVariables: Map<String, NodeType> = emptyMap(),
    private val printValues: List<String> = emptyList(),
) {
    fun getVariable(name: String): Any? {
        return assignedVariables[name]
    }

    fun getConstant(name: String): Any? {
        return constants[name]
    }

    fun update(other: Context): Context {
        val updatedAssignedVariables = assignedVariables + other.assignedVariables
        val updatedConstants = constants + other.constants
        val updatedDeclaredVariables = declaredVariables + other.declaredVariables
        val updatedPrintValues = printValues + other.printValues

        return Context(updatedAssignedVariables, updatedConstants, updatedDeclaredVariables, updatedPrintValues)
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

    fun isInContext(name: String): Boolean {
        return isVariableDeclared(name) || isVariableAssigned(name) || isConstantAssigned(name)
    }

    fun updateVariableValues(other: Context): Context {
        val updatedAssignedVariables =
            assignedVariables.mapValues { (name, value) ->
                if (declaredVariables.containsKey(name)) {
                    other.assignedVariables[name] ?: value
                } else {
                    value
                }
            }

        return Context(
            updatedAssignedVariables,
            constants,
            declaredVariables,
            printValues,
        )
    }
}
