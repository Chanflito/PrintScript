package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.NumberNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.common.token.ValueNumber
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.validator.Validator

class ValueNumberParser(override val validator: Validator) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        val token = tokens.find { it.tokenType == ValueNumber }
        return ASTNodeImpl(
            token?.value?.toDouble(),
            token,
            NumberNode,
            emptyList()
        )
    }

    override fun canParse(tokens: List<Token>): Boolean {
        return validator.validate(tokens)
    }
}