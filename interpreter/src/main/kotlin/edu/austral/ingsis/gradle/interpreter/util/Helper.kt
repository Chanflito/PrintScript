package edu.austral.ingsis.gradle.interpreter.util

import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType

fun doesTypeMatch(
    result: Any,
    type: NodeType,
): Boolean {
    return when (type) {
        StringNodeType -> result is String
        NumberNodeType -> result is Int
        BooleanNodeType -> result is Boolean
        else -> false
    }
}
