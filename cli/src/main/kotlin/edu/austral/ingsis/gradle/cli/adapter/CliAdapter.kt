package edu.austral.ingsis.gradle.cli.adapter

interface CliAdapter<in T> {
    fun adapt(input: T): String
}
