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
                continue
            }

            val char = readResult.toChar()

            if (char == ';' && block.isEmpty()) {
                return statementBuilder.append(char).toString()
            }

            if (char == '{') {
                block.push(char)
            }

            if (containsLeftBrace(char, block)) return statementBuilder.append(char).toString()

            statementBuilder.append(char)
        }
        return statementBuilder.toString()
    }

    private fun containsLeftBrace(
        char: Char,
        block: Stack<Char>,
    ): Boolean {
        if (char == '}' && haveLeftBrace(block)) {
            block.pop()
            if (block.isEmpty()) {
                return true
            }
        }
        return false
    }

    private fun haveLeftBrace(stack: Stack<Char>): Boolean {
        return stack.isNotEmpty() && stack.peek() == '{'
    }
}
