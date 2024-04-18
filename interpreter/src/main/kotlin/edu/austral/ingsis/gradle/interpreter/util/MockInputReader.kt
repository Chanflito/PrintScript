package edu.austral.ingsis.gradle.interpreter.util

class MockInputReader : InputReader {
    override fun read(): Any {
        return "mocked input"
    }
}
