package edu.austral.ingsis.gradle.interpreter.util

class MockInputReader : InputReader {
    override fun read(message: String): Any {
        return message
    }
}
