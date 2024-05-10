package edu.austral.ingsis.gradle.common.ast

sealed interface NodeType

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
