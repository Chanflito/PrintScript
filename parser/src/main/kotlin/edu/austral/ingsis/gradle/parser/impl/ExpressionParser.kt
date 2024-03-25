package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.Parser
//Here should go expressions like 5+5, 7+2
class ExpressionParser(private val index: Int) : Parser {

    override fun parse(tokens: List<Token>): ASTNode {
        TODO("Not yet implemented")
    }
}