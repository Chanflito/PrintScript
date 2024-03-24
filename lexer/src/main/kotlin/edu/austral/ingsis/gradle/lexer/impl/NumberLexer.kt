package edu.austral.ingsis.gradle.lexer.impl

import common.token.Token
import common.token.ValueNumber
import lexer.edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

class NumberLexer : Lexer {
    private val regex = RegexPatterns.NUMBER_REGEX
    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { result ->
            if (isInQuotes(result, code)) null
            else createToken(result, code, ValueNumber)
        }.toList()
    }
}