package edu.austral.ingsis.gradle.sca.adapter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.sca.Rule

interface ScaAdapter<in T> {
    fun adapt(input: T): Rule<AST>
}
