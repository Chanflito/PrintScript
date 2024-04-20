package edu.austral.ingsis.gradle.iterator

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class FileBuffer(private val file: InputStream) {
    private val bufferReader = BufferedReader(InputStreamReader(file))

    companion object {
        const val MAX_LINES = 500
    }

    fun canRead(): Boolean {
        bufferReader.mark(MAX_LINES)
        val canRead = bufferReader.readLine() != null
        bufferReader.reset()
        return canRead
    }

    fun getFileBuffered() = bufferReader
}
