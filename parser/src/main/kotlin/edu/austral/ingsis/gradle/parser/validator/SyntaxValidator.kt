package edu.austral.ingsis.gradle.parser.validator
import edu.austral.ingsis.gradle.common.token.Token

interface SyntaxValidator {
    fun validate(tokens: List<Token>, index: Int): Boolean
}

