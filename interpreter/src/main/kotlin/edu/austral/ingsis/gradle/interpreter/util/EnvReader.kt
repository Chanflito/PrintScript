package edu.austral.ingsis.gradle.interpreter.util

interface EnvReader {
    fun readEnv(key: String): Any
}
