package edu.austral.ingsis.gradle.iterator

import edu.austral.ingsis.gradle.iterator.util.MockInputStream
import org.junit.jupiter.api.Test

class FileLargeTest {
    @Test
    fun testWithLargeFile() {
        executeWithBuffer(MockInputStream("println(\"Hello World \");\n", 32000))
    }
}
