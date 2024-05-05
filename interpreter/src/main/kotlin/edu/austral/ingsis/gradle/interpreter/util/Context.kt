package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.NodeType

/**
 * Context class represents the context in which code is being interpreted.
 * It keeps track of assigned variables, constants, and declared variables.
 *
 * @property assignedVariables Map of variables that already have values assigned with their names as keys and their values as values.
 * @property constants Map of constants with their names as keys and their values as values.
 * @property declaredVariablesAndConstants Map of all declared variables and constants with their names as keys and their types as values.
 */

class Context(
    private val assignedVariables: Map<String, Any> = emptyMap(),
    private val constants: Map<String, Any> = emptyMap(),
    private val declaredVariablesAndConstants: Map<String, NodeType> = emptyMap(),
) {
    fun getVariable(name: String): Any? {
        return assignedVariables[name]
    }

    fun getConstant(name: String): Any? {
        return constants[name]
    }

    /**
     * Updates the context with values from another context.
     *
     * @param other The other context whose values to merge with this context.
     * @return The updated context.
     */

    fun update(other: Context): Context {
        val updatedAssignedVariables = assignedVariables + other.assignedVariables
        val updatedConstants = constants + other.constants
        val updatedDeclaredVariables = declaredVariablesAndConstants + other.declaredVariablesAndConstants

        return Context(updatedAssignedVariables, updatedConstants, updatedDeclaredVariables)
    }

    fun getVariableType(name: String): NodeType? {
        return declaredVariablesAndConstants[name]
    }

    fun isVariableDeclared(name: String): Boolean {
        return declaredVariablesAndConstants.containsKey(name)
    }

    fun isVariableAssigned(name: String): Boolean {
        return assignedVariables.containsKey(name)
    }

    fun isConstantAssigned(name: String): Boolean {
        return constants.containsKey(name)
    }

    fun isInContext(name: String): Boolean {
        return isVariableDeclared(name) || isVariableAssigned(name) || isConstantAssigned(name)
    }

    /**
     * Updates variable values in the context with values from another context.
     * Values are updated only for variables that are declared.
     * Used for interpreting inner blocks of code where only changes to variables created outside the block are saved
     */

    fun updateVariableValues(other: Context): Context {
        val updatedAssignedVariables =
            assignedVariables.mapValues { (name, value) ->
                if (declaredVariablesAndConstants.containsKey(name)) {
                    other.assignedVariables[name] ?: value
                } else {
                    value
                }
            }

        return Context(
            updatedAssignedVariables,
            constants,
            declaredVariablesAndConstants,
        )
    }

    fun getVariableOrConstant(name: String): Any? {
        return assignedVariables[name] ?: constants[name]
    }
}
