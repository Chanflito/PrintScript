package edu.austral.ingsis.gradle.common.ast

interface OperatorNodeType

data object SumOperatorNode : OperatorNodeType

data object SubtractOperatorNode : OperatorNodeType

data object MultiplyOperatorNode : OperatorNodeType

data object DivideOperatorNode : OperatorNodeType
