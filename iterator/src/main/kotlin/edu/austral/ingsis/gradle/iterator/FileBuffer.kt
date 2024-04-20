package edu.austral.ingsis.gradle.iterator

import java.io.BufferedReader
import java.io.InputStream

class FileBuffer(private val file: InputStream) {
    private val bufferReader = BufferedReader(file.bufferedReader())

    fun getFileBuffered() = bufferReader
}
