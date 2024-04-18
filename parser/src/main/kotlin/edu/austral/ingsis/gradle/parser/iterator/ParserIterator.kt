package edu.austral.ingsis.gradle.parser.iterator

import edu.austral.ingsis.gradle.common.ast.newast.AST
import edu.austral.ingsis.gradle.lexer.iterator.LexerIterator
import edu.austral.ingsis.gradle.parser.InputContext
import edu.austral.ingsis.gradle.parser.Parser

class ParserIterator(
    private val lexerIterator: LexerIterator,
    private val parser: Parser<InputContext>,
) : Iterator<AST?> {
    private var previousAST: AST? = null
    private var previousIndex: Int = 0

    override fun hasNext(): Boolean {
        if (lexerIterator.hasNext()) {
            parseNewTokenList()
        }
        return previousAST != null
    }

    override fun next(): AST? {
        if (isPreviousAstNullAndLexerIteratorEmpty()) {
            return null
        }
        if (isPreviousAstNullAndLexerIteratorNotEmpty()) {
            parseNewTokenList()
        }
        val astCreated = previousAST
        previousAST = null
        return astCreated
    }

    private fun parseNewTokenList() {
        val tokens = lexerIterator.next()
        val inputContext = InputContext(tokens, previousIndex)
        val (ast, index) = parser.parse(inputContext)
        previousIndex = 0
        previousAST = ast
    }

    private fun isPreviousAstNullAndLexerIteratorEmpty(): Boolean {
        return previousAST == null && !lexerIterator.hasNext()
    }

    private fun isPreviousAstNullAndLexerIteratorNotEmpty(): Boolean {
        return previousAST == null && lexerIterator.hasNext()
    }
}
