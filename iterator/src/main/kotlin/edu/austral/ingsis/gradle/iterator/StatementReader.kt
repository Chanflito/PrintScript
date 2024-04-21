package edu.austral.ingsis.gradle.iterator

import java.io.BufferedReader
import java.util.Stack

class StatementReader {
    fun read(reader: BufferedReader): String {
        val statementBuilder = StringBuilder()
        val block = Stack<Char>()
        var endOfStatement = false
        var elseReached = false
        while (!endOfStatement) {
            val line = reader.readLine()

            if (line == null) {
                endOfStatement = true
            } else {
                for (char in line) {
                    when {
                        char == ';' && block.isEmpty() ->
                            return statementBuilder.append(char).toString()
                        char == '{' -> block.push(char)
                        char == '}' && haveLeftBrace(block) -> {
                            block.pop()
                            if (line.contains("else")) {
                                statementBuilder.append(char)
                                elseReached = true
                                continue
                            }
                            if (containsElseOnNextLine(reader)) {
                                elseReached = true
                                statementBuilder.append(char)
                                continue
                            }
                            if (elseReached) {
                                return statementBuilder.append(char).toString()
                            }
                        }
                    }
                    statementBuilder.append(char)
                }
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

    private fun containsElseOnNextLine(reader: BufferedReader): Boolean {
        reader.mark(3)
        val line = reader.readLine()
        val canRead = line != null && line.contains("else")
        reader.reset()
        return canRead
    }
}
