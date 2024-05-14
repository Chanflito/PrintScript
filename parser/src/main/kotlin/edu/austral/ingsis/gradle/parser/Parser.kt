package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.token.Token

interface Parser<in T : ParserInput> {
    fun parse(input: T): Pair<AST, Int>
}

interface ParserInput

data class InputContext(val tokens: List<Token>, val index: Int = 0) : ParserInput
