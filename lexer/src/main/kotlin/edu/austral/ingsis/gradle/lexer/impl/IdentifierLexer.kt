package edu.austral.ingsis.gradle.lexer.impl

import edu.austral.ingsis.gradle.common.token.Identifier
import edu.austral.ingsis.gradle.common.token.Token
import edu.austral.ingsis.gradle.lexer.Lexer
import edu.austral.ingsis.gradle.lexer.util.RegexPatterns
import edu.austral.ingsis.gradle.lexer.util.createToken
import edu.austral.ingsis.gradle.lexer.util.isInQuotes

/**
 @param constraints: List of strings that should not be considered as identifiers. Should include all keywords and types.
**/
class IdentifierLexer(private val constraints: List<String>) : Lexer {
    private val regex = RegexPatterns.IDENTIFIER_REGEX

    override fun splitIntoTokens(code: String): List<Token> {
        return regex.findAll(code).mapNotNull { matchResult ->
            if (isMatch(matchResult, code)) {
                createToken(matchResult, code, Identifier)
            } else {
                null
            }
        }.toList()
    }

    private fun isMatch(
        matchResult: MatchResult,
        code: String,
    ) = !constraints.contains(matchResult.value) && !isInQuotes(matchResult, code)
}
