package edu.austral.ingsis.gradle.iterator

import edu.austral.ingsis.gradle.lexer.director.LexerDirector
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileInputStream
import kotlin.test.assertTrue

class LexerIteratorTest {
    @Test
    fun `test lexer iterator with V1-0`() {
        val lexer = LexerDirector().createComposeLexer("1.0")
        val inputStream = FileInputStream("src/test/resources/input_iterator/input_001_iterator.txt")
        val iterator = LexerIterator(lexer, inputStream)
        val file = File("src/test/resources/expected_iterator/expected_001_iterator.txt").toString()
        isEquals(iterator, file)
    }

    @Test
    fun `test lexer iterator with V1-1`() {
        val lexer = LexerDirector().createComposeLexer("1.1")
        val inputStream = FileInputStream("src/test/resources/input_iterator/input_002_iterator.txt")
        val iterator = LexerIterator(lexer, inputStream)
        val file = File("src/test/resources/expected_iterator/expected_002_iterator.txt").toString()
        isEquals(iterator, file)
    }

    private fun isEquals(
        iterator: LexerIterator,
        file: String,
    ) {
        var currentPosition = 0
        while (iterator.hasNext()) {
            val tokens = iterator.next()
            val expectedTokens = convertFileIntoListOfString(File(file), currentPosition).first
            currentPosition = convertFileIntoListOfString(File(file), currentPosition).second
            assert(tokens.size == expectedTokens.size)
            assertTrue(compareTokenListWithIterator(tokens, expectedTokens))
        }
    }

    private fun convertFileIntoListOfString(
        file: File,
        position: Int,
    ): Pair<List<String>, Int> {
        val lines = file.readLines()
        val resultList = mutableListOf<String>()
        var currentPosition = position
        var foundEndOfStatement = false
        for (i in currentPosition until lines.size) {
            val line = lines[i]
            if (line.trim() == "END_OF_STATEMENT") {
                foundEndOfStatement = true
                currentPosition = i + 1 // Set the current position to the next line
                break
            }
            resultList.add(line)
        }

        if (!foundEndOfStatement) {
            currentPosition = lines.size // Set current position to the end of file
        }

        return Pair(resultList, currentPosition)
    }
}
