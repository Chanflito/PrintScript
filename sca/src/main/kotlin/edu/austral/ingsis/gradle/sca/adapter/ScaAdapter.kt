package edu.austral.ingsis.gradle.sca.adapter

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.sca.Rule

interface ScaAdapter<in T> {
    /**
     * You should implement how to adapt the input to an AST
     * @param input could be a file, json object etc
     * @return the adapted AST
     *
     */
    fun adapt(input: T): Rule<AST>
}
