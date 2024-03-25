package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.Parser

//Here should go assignations like let a : number= 7; only with let keyword
class DeclarationParser (private val index: Int) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        TODO("Not yet implemented")
    }
}