package edu.austral.ingsis.gradle.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.io.File

class ParserIteratorWithInterpreterTest {
    @Test
    fun test001() {
        val input = "src/test/resources/test.ps"
        execute(File(input).inputStream())
    }

    @Test
    fun test002() {
        val input = "src/test/resources/a.ps"
        execute(File(input).inputStream())
    }

    @Test
    fun test003() {
        val input = "src/test/resources/b.ps"
        assertDoesNotThrow { execute(File(input).inputStream()) }
    }
}
