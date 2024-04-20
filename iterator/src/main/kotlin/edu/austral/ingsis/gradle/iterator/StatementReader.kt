package edu.austral.ingsis.gradle.iterator

import java.io.BufferedReader
import java.util.Stack

class StatementReader {
    fun read(reader: BufferedReader): String {
        val statementBuilder = StringBuilder()
        val block = Stack<Char>()
        var endOfStatement = false

        while (!endOfStatement) {
            val readResult = reader.read()

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
