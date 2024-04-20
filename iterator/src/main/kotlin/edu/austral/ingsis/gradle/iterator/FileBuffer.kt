package edu.austral.ingsis.gradle.iterator

import java.io.InputStream

class FileBuffer(private val file: InputStream) {
    private val bufferReader = file.bufferedReader()
    private var currentLine = 0

    companion object {
        const val MAX_LINES = 500
    }

    fun bufferFile(): String {
        var counter = 0
        val bufferedFile = StringBuilder()
        bufferReader.skip(currentLine.toLong())
        while (currentLine < MAX_LINES) {
            val line = bufferReader.readLine() ?: break
            bufferedFile.append(line)
            counter++
        }
        currentLine += counter
        bufferReader.close()
        return bufferedFile.toString()
    }
}
