package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.common.ast.newast.BlockNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.util.createComposeParser
import edu.austral.ingsis.gradle.parser.util.isRightBrace

class BlockParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<BlockNode, Int> {
        val childrenList = mutableListOf<AST>()
        val currentToken = input.tokens[input.index]
        var index = input.index
        while (!isRightBrace(input.tokens[index])) {
            val (node, newIndex) = createComposeParser().parse(InputContext(input.tokens, index))
            childrenList.add(node)
            index = newIndex
        }
        return Pair(
            BlockNode(
                currentToken.tokenPosition,
                childrenList,
            ),
            index + 1,
        )
    }
}
