package edu.austral.ingsis.gradle.cli.adapter

import java.io.File

class FileAdapter : CliAdapter<File> {
    override fun adapt(input: File): String {
        return input.readText()
    }
}
