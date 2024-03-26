package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.token.Token

interface Parser<in T : ParserInput> { // TODO implement Output context with out.
    fun parse(input: T): Pair<ASTNode, Int>
}

interface ParserInput

data class InputContext(val tokens: List<Token>, val index: Int) : ParserInput
