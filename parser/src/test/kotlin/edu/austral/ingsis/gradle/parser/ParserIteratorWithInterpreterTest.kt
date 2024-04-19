package edu.austral.ingsis.gradle.parser

import org.junit.jupiter.api.Test

class ParserIteratorWithInterpreterTest {
    @Test
    fun test001() {
        val input = "const b: string = \"this should be valid in 1.1\";"
        execute(input)
    }

    @Test
    fun test002() {
        val input = "let cuenta: number = 5*5-8/4+2;"
        execute(input)
    }
}
