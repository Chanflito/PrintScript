package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.IfElseStatementNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.MissingTokenException
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.endOfFile
import edu.austral.ingsis.gradle.parser.util.isElseKeyword
import edu.austral.ingsis.gradle.parser.util.isLeftBrace

class IfParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        // first parse the if block
        val (ifBlock, ifBlockIndex) = IfStatementParser().parse(input)
        // then consume the closing brace of the if block
        val (_, closingIfIndex) = consumeToken(input.tokens, ifBlockIndex)
        // if the index reaches or passes the end of the tokens list return the parsed node
        if (endOfFile(input.tokens, closingIfIndex)) return Pair(ifBlock, ifBlockIndex)
        // else keep parsing the else block
        val (elseBlock, elseBlockIndex) = consumeToken(input.tokens, closingIfIndex)

        // if the next index isn't an else keyword return the if statement and keep parsing
        if (!isElseKeyword(elseBlock)) return Pair(ifBlock, ifBlockIndex)

        val (leftBraceToken, leftBraceIndex) = consumeToken(input.tokens, elseBlockIndex)

        if (!isLeftBrace(leftBraceToken)) throw MissingTokenException(leftBraceToken, "{")

        val (block, endBlockIndex) = BlockParser().parse(InputContext(input.tokens, leftBraceIndex))

        return Pair(
            IfElseStatementNode(
                ifBlock.tokenPosition,
                ifBlock.condition,
                ifBlock.ifBlock,
                block,
            ),
            endBlockIndex,
        )
    }
}
