package edu.austral.ingsis.gradle.interpreter.util

interface InputReader {
    fun read(message: String): Any
}
