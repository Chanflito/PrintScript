package edu.austral.ingsis.gradle.iterator

import java.io.BufferedReader

class StatementIterator(private val reader: BufferedReader) : Iterator<String> {
    private val statementReader = StatementReader()
    private var previousStatement = ""

    override fun hasNext(): Boolean {
        if (previousStatement.isEmpty()) {
            previousStatement = statementReader.read(reader) // saves the statement red from the input stream
        }
        return previousStatement.isNotEmpty()
    }

    override fun next(): String {
        val currentStatement = previousStatement.ifEmpty { statementReader.read(reader) }
        previousStatement = ""
        val nextStatement = statementReader.read(reader).trim() // this was added for /n cases
        previousStatement = nextStatement
        return currentStatement
    }

    private fun startsWithElse(statement: String): Boolean {
        return statement.trim().startsWith("else")
    }
}
