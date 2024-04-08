package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.interpreter.newinterpreter.Interpreter
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterNewTest {
    @Test
    fun test001_ShouldInterpretLetDeclarationAndReAssignation() {
        val input = input_001n
        val expected = "Hello World"
        val interpreter = Interpreter()
        interpreter.interpret(input)
        assert(interpreter.assignedVariables["a"] == expected)
        assert(interpreter.printedElements[0] == expected)
    }

    @Test
    fun test002_ShouldThrowErrorWhenTypeMismatch() {
        val input = input_002n
        val interpreter = Interpreter()
        assertThrows<RuntimeException> {
            interpreter.interpret(input)
        }
    }

    @Test
    fun test003_ShouldReturnNewMessageWhenConcatenatingStringsAndNumbers() {
        val input = input_003n
        val expected = "Hello World5"
        val interpreter = Interpreter()
        interpreter.interpret(input)
        assert(interpreter.printedElements[0] == expected)
    }
}
