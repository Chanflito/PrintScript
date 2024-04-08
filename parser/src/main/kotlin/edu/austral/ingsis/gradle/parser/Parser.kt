package edu.austral.ingsis.gradle.parser

import edu.austral.ingsis.gradle.common.ast.ASTNode
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.parser.validator.Validator

interface Parser {
    val validator: Validator;
    fun parse(tokens: List<Token>): ASTNode
    fun canParse(tokens: List<Token>): Boolean
}

interface ComposeParser : Parser {
    val parsers: List<Parser>
}