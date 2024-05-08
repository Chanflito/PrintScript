package edu.austral.ingsis.gradle.interpreter.util

/**
 * Reads environment values
 */
interface EnvReader {
    fun readEnv(key: String): Any
}
