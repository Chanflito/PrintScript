package edu.austral.ingsis.gradle.interpreter.util

class KotlinInputReader : InputReader {
    override fun read(): Any {
        return readln()
    }
}
