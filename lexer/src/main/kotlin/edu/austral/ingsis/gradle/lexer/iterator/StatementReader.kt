package edu.austral.ingsis.gradle.lexer.iterator

import java.io.InputStream
import java.util.Stack

class StatementReader {
    fun read(inputStream: InputStream): String {
        val statementBuilder = StringBuilder()
        val block = Stack<Char>()
        var endOfStatement = false

        while (!endOfStatement) {
            val readResult = inputStream.read()

            if (readResult == -1) {
                endOfStatement = true
            } else {
                val char = readResult.toChar()

                when {
                    char == ';' && block.isEmpty() -> return statementBuilder.append(char).toString()
                    char == '{' -> block.push(char)
                    char == '}' && haveLeftBrace(block) -> {
                        block.pop()
                        if (block.isEmpty()) {
                            return statementBuilder.append(char).toString()
                        }
                    }
                }

                statementBuilder.append(char)
            }
        }
        return statementBuilder.toString()
    }

    private fun haveLeftBrace(stack: Stack<Char>): Boolean {
        if (stack.isNotEmpty()) {
            return stack.peek() == '{'
        }
        return false
    }
}
