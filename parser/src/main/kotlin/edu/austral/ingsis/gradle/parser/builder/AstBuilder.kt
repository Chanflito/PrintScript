package edu.austral.ingsis.gradle.parser.builder

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.token.Token

interface AstBuilder<T : ASTNode> {
    fun build(
        tokens: List<Token>,
        index: Int,
    ): Pair<T, Int>
}
