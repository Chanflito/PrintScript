package edu.austral.ingsis.gradle.parser.builder

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.token.Token

interface AstBuilder<T : AST> {
    fun build(
        tokens: List<Token>,
        index: Int,
    ): Pair<T, Int>
}
