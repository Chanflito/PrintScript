package edu.austral.ingsis.gradle.iterator.util
import java.io.InputStream

class MockInputStream(line: String, private val numberOfLines: Int) : InputStream() {
    var lineNumber = 0
    private var index = 0
    private val lineBytes: IntArray = line.toCharArray().map { it.toInt() }.toIntArray()

    override fun read(): Int {
        if (index >= lineBytes.size) {
            index = 0
            lineNumber++
        }

        return if (lineNumber < numberOfLines) {
            val byteValue = lineBytes[index]
            index++
            byteValue
        } else {
            -1
        }
    }
}
