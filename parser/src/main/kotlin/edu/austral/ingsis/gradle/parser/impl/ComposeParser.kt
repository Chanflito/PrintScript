package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.token.TokenType
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.CustomException
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.NoParserFoundException
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.endOfFile
import edu.austral.ingsis.gradle.parser.util.isRightBrace
import edu.austral.ingsis.gradle.parser.util.isSemiColon

class ComposeParser(
    private val parsers: Map<TokenType, Parser<InputContext>>,
) :
    Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val result = currentToken(input.tokens, input.index)
        val parserFound = parsers[result.tokenType] ?: throw NoParserFoundException(result)
        return parseWith(input, parserFound)
    }

    private fun parseWith(
        input: InputContext,
        parser: Parser<InputContext>,
    ): Pair<AST, Int> {
        // result of the parser, including the ast node (.first) and the next index to parse (.second)
        val result = parser.parse(input)
        // if the index reaches or passes the end of the tokens list return the parsed node
        if (endOfFile(input.tokens, result.second)) {
            throw CustomException("Missing ';' or '}' at the end of the line")
        }
        val nextToken = currentToken(input.tokens, result.second)
        // if the next token isn't a right brace or a semicolon, throw exception. Remember this parser only parses one line at the time
        if (!isSemiColon(nextToken) && !isRightBrace(nextToken)) {
            throw MissingTokenException(nextToken, "';' or '}'")
        }
        return result
    }
}
