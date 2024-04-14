package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.interpreter.newinterpreter.ProgramNodeInterpreter
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterNewTest {

    @Test
    fun `should interpret programNode with declarationAssignation`() {
        val programNode = input_001n
        val interpreter = ProgramNodeInterpreter(programNode)
        val result = interpreter.interpret()
        result.isContextResult()
        val context = result as InterpretResult.ContextResult
        assert(context.context.isVariableAssigned("x"))
    }

    @Test
    fun `should interpret programNode with declarationAssignation and ReAssignation with sum of String literals`() {
        val programNode = input_002n
        val interpreter = ProgramNodeInterpreter(programNode)
        val result = interpreter.interpret()
        result.isContextResult()
        val context = result as InterpretResult.ContextResult
        assert(context.context.isVariableAssigned("x"))
        val operationResult = context.context.getVariable("x")
        if (operationResult != null) {
            assert(operationResult.getValue() == "holamundo")
        }
    }

    @Test
    fun `should interpret programNode with declarationAssignation and ReAssignation with sum of Number literals`() {
        val programNode = input_003n
        val interpreter = ProgramNodeInterpreter(programNode)
        val result = interpreter.interpret()
        result.isContextResult()
        val context = result as InterpretResult.ContextResult
        assert(context.context.isVariableAssigned("x"))
        val operationResult = context.context.getVariable("x")
        if (operationResult != null) {
            assert(operationResult.getValue() == 3)
        }
    }

    @Test
    fun `should throw exception when variable is not declared`() {
        val programNode = input_004n
        val interpreter = ProgramNodeInterpreter(programNode)
        assertThrows<RuntimeException> {
            interpreter.interpret()
        }
    }

    @Test
    fun `should throw exception when variable is already declared`() {
        val programNode = input_005n
        val interpreter = ProgramNodeInterpreter(programNode)
        assertThrows<RuntimeException> {
            interpreter.interpret()
        }
    }

    @Test
    fun `should identify and print a value that is declared and has a value assigned`() {
        val programNode = input_006n
        val interpreter = ProgramNodeInterpreter(programNode)
        val result = interpreter.interpret()
        result.isContextResult()
        val contextResult = result as InterpretResult.ContextResult
        assert(contextResult.context.getPrintValues()[0] == "1")
    }

    @Test
    fun `should throw exception when trying to reAssign a constant`() {
        val programNode = input_007n
        val interpreter = ProgramNodeInterpreter(programNode)
        assertThrows<RuntimeException> {
            interpreter.interpret()
        }
    }

    @Test
    fun `should enter if when condition is true and declare a new variable`() {
        val programNode = input_008n
        val interpreter = ProgramNodeInterpreter(programNode)
        val result = interpreter.interpret()
        result.isContextResult()
        val contextResult = result as InterpretResult.ContextResult
        assert(contextResult.context.isVariableAssigned("x"))
    }

    @Test
    fun `should enter else when condition is false and declare a new variable`() {
        val programNode = input_009n
        val interpreter = ProgramNodeInterpreter(programNode)
        val result = interpreter.interpret()
        result.isContextResult()
        val contextResult = result as InterpretResult.ContextResult
        assert(contextResult.context.isVariableAssigned("z"))
        assert(!contextResult.context.isVariableAssigned("y"))
    }

    @Test
    fun `should interpret ReadEnv and return an environment variable with the assigned type`() {
        val programNode = input_010n
        val interpreter = ProgramNodeInterpreter(programNode)
        val result = interpreter.interpret()
        result.isContextResult()
        val contextResult = result as InterpretResult.ContextResult
        assert(contextResult.context.isVariableAssigned("a"))
        val operationResult = contextResult.context.getVariable("a")
        val expectedValue = System.getenv("JAVA_HOME")
        if (operationResult != null) {
            assert(operationResult.getValue() ==expectedValue )
        }

    }





}
