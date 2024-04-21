package edu.austral.ingsis.gradle.iterator

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class FileBuffer(private val file: InputStream) {
    private val bufferReader = BufferedReader(InputStreamReader(file))

    companion object {
        const val READ_AHEAD_LIMIT = 8000
    }

    fun canRead(): Boolean {
        bufferReader.mark(READ_AHEAD_LIMIT)
        val canRead = bufferReader.readLine() != null
        bufferReader.reset()
        return canRead
    }

    fun getFileBuffered() = bufferReader
}
