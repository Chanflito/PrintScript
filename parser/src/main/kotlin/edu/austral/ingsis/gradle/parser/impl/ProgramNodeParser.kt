package edu.austral.ingsis.gradle.parser.impl

import edu.austral.ingsis.gradle.common.ast.AST
import edu.austral.ingsis.gradle.common.ast.ProgramNode
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser
import edu.austral.ingsis.gradle.parser.factory.createComposeParser
import edu.austral.ingsis.gradle.parser.util.endOfFile
import edu.austral.ingsis.gradle.parser.util.isSemiColon

class ProgramNodeParser : Parser<InputContext> {
    override fun parse(input: InputContext): Pair<AST, Int> {
        val asts = mutableListOf<AST>()
        var inputIndex = input.index
        while (!endOfFile(input.tokens, inputIndex) && !isSemiColon(input.tokens[inputIndex])) {
            val (node, newIndex) = createComposeParser().parse(InputContext(input.tokens, inputIndex))
            asts.add(node)
            inputIndex = newIndex + 1
        }
        return Pair(ProgramNode(input.tokens[0].tokenPosition, asts), inputIndex)
    }
}
