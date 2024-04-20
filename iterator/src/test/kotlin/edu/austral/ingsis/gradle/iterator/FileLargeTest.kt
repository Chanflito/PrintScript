package edu.austral.ingsis.gradle.iterator

import edu.austral.ingsis.gradle.iterator.util.MockInputStream
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class FileLargeTest {
    @Test
    fun testWithLargeFile() {
        assertDoesNotThrow { execute(MockInputStream("println(\"Hello World \");\n", 32000)) }
    }
}
