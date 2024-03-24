package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.token.Token

interface Parser {
    fun parse(tokens: List<Token>): ASTNode
}