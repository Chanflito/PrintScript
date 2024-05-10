package edu.austral.ingsis.gradle.common.ast

sealed interface Assignation : Statement {
    val expression: Expression
}
