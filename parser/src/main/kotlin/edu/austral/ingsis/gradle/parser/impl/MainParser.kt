package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.ComposeParser
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.isSemiColon
import edu.austral.ingsis.gradle.parser.validator.Validator

class MainParser(override val parsers: List<Parser>, override val validator: Validator) : ComposeParser {

    override fun parse(tokens: List<Token>): ASTNode {
        val (semicolons, rest) = splitPerSemicolon(tokens)
        var ast = ASTNodeImpl("Program", null, ProgramNode, emptyList())
        parsers.forEach {
            if (it.canParse(tokens)) {
                ast = ast.addChild(it.parse(tokens))
            }
        }
        return ast
    }

    private fun splitPerSemicolon(tokens: List<Token>) =
        tokens.partition { it -> isSemiColon(it) }

    override fun canParse(tokens: List<Token>): Boolean {
        TODO("Not yet implemented")
    }
}