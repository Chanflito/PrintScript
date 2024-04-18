package edu.austral.ingsis.gradle.interpreter.util

class KotlinEnvReader : EnvReader {
    override fun readEnv(key: String): Any {
        // remove quotes from key
        if (key.startsWith("\"") && key.endsWith("\"")) {
            return System.getenv(key.substring(1, key.length - 1))
        }
        return System.getenv(key)
    }
}
