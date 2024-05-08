package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType

/**
 * Checks if NodeType matches with different value types.
 */
fun doesTypeMatch(
    result: Any,
    type: NodeType,
): Boolean {
    return when (type) {
        StringNodeType -> result is String
        NumberNodeType -> result is Number
        BooleanNodeType -> result is Boolean
        else -> false
    }
}

/**
 * Casts double to int if it has no decimal values
 */
fun castToDesiredType(num: Number): Number {
    return when (num) {
        is Double -> if (num % 1 == 0.0) num.toInt() else num
        else -> num
    }
}
