package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.builder.createComposeParser
import edu.austral.ingsis.gradle.parser.util.currentToken
import edu.austral.ingsis.gradle.parser.util.isRightBrace

class BlockParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<BlockNode, Int> {
        val (tokens, index) = input
        val childrenList = mutableListOf<AST>()
        val blockToken = currentToken(tokens, index)
        var currentIndex = index
        while (!isRightBrace(input.tokens[currentIndex])) {
            val (node, newIndex) = createComposeParser().parse(InputContext(tokens, currentIndex))
            childrenList.add(node)
            currentIndex = newIndex + 1
        }
        return Pair(
            BlockNode(
                blockToken.tokenPosition,
                childrenList,
            ),
            currentIndex,
        )
    }
}
