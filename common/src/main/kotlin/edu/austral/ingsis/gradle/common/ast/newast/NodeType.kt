package edu.austral.ingsis.gradle.common.ast.newast

interface NodeType

object BooleanNodeType : NodeType {
    override fun toString(): String {
        return "Boolean"
    }
}

object StringNodeType : NodeType {
    override fun toString(): String {
        return "String"
    }
}

object NumberNodeType : NodeType {
    override fun toString(): String {
        return "Number"
    }
}
