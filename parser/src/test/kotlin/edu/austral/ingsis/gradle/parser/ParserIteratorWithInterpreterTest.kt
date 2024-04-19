package edu.austral.ingsis.gradle.parser

import org.junit.jupiter.api.Test
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
}
