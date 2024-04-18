package edu.austral.ingsis.gradle.interpreter.util

class KotlinEnvReader: EnvReader{

    override fun readEnv(key: String): Any {
        return System.getenv(key)
    }

}