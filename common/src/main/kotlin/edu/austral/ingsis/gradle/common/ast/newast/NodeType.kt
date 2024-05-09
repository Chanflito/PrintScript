package edu.austral.ingsis.gradle.common.ast.newast

interface NodeType

object NumberNodeType : NodeType {
    override fun toString(): String {
        return "Number"
    }
}

object StringNodeType : NodeType {
    override fun toString(): String {
        return "String"
    }
}

object BooleanNodeType : NodeType {
    override fun toString(): String {
        return "Boolean"
    }
}
