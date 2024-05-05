package edu.austral.ingsis.gradle.interpreter.util

/**
 * Reads user input.
 */
interface InputReader {
    fun read(message: String): Any
}
