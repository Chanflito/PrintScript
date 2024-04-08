package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.StringNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.isStringToken
import edu.austral.ingsis.gradle.parser.validator.Validator

class ValueStringParser(override val validator: Validator) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        val token = tokens.find { isStringToken(it) }
        return ASTNodeImpl(
            token?.value,
            token,
            StringNode,
            emptyList()
        )
    }

    override fun canParse(tokens: List<Token>): Boolean {
        return validator.validate(tokens)
    }
}