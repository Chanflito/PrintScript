package edu.austral.ingsis.gradle.cli.adapter

import java.io.File

class FileAdapter : CliAdapter<File?> {
    override fun adapt(input: File?): String {
        if (input == null) {
            throw IllegalArgumentException("File not found")
        }
        return input.readText()
    }
}
