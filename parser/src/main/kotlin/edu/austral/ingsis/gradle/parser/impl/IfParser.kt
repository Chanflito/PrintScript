package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.IfElseStatement
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.ExpectedTokenErrorMessage
import edu.austral.ingsis.gradle.parser.util.consumeToken
import edu.austral.ingsis.gradle.parser.util.isElseKeyword
import edu.austral.ingsis.gradle.parser.util.isLeftBrace

class IfParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val (ifBlock, ifBlockIndex) = IfStatementParser().parse(input)

        val (elseBlock, elseBlockIndex) = consumeToken(input.tokens, ifBlockIndex)

        if (!isElseKeyword(elseBlock)) return Pair(ifBlock, ifBlockIndex)

        val (leftBraceToken, leftBraceIndex) = consumeToken(input.tokens, elseBlockIndex)

        if (!isLeftBrace(leftBraceToken)) throw Exception(ExpectedTokenErrorMessage("{", leftBraceToken!!).toString())

        val (block, endBlockIndex) = BlockParser().parse(InputContext(input.tokens, leftBraceIndex))

        return Pair(
            IfElseStatement(
                ifBlock.tokenPosition,
                ifBlock.condition,
                ifBlock.ifBlock,
                block,
            ),
            endBlockIndex,
        )
    }
}
