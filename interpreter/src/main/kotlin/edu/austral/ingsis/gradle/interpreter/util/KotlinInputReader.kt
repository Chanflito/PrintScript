package edu.austral.ingsis.gradle.interpreter.util

class KotlinInputReader : InputReader {
    override fun read(message: String): Any {
        println(message)
        return readln()
    }
}
