package edu.austral.ingsis.gradle.interpreter.util

class MockInputReader(val value: Any) : InputReader {
    override fun read(message: String): Any {
        return value
    }
}
