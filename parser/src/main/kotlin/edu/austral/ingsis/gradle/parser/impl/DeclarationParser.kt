package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.ast.ASTNodeImpl
import edu.austral.ingsis.gradle.common.ast.IdentifierNode
import edu.austral.ingsis.gradle.common.ast.KeywordNode
import edu.austral.ingsis.gradle.common.token.*
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.validator.Validator

class DeclarationParser(override val validator: Validator) : Parser {
    override fun parse(tokens: List<Token>): ASTNode {
        val identifier = tokens.find { it.tokenType == Identifier }
        val keyword = tokens.find { it.tokenType in setOf(LetKeyword) }
        val declarationType = tokens.find { it.tokenType in setOf(TypeNumber, TypeString) }
        var ast = ASTNodeImpl(identifier?.value, identifier, IdentifierNode, emptyList())

        if (keyword != null && declarationType != null) {
            val keywordNode = ASTNodeImpl(keyword.value, keyword, KeywordNode, emptyList())
            val declarationTypeNode = ASTNodeImpl(declarationType.value, declarationType, IdentifierNode, emptyList())
            ast = ast.addChild(keywordNode)
            ast = ast.addChild(declarationTypeNode)
        }


        return ast
    }

    override fun canParse(tokens: List<Token>): Boolean {
        return validator.validate(tokens)
    }
}