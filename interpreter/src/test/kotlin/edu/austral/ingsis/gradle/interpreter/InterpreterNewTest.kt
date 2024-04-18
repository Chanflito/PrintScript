package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.KotlinEnvReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinInputReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinPrinter
import edu.austral.ingsis.gradle.interpreter.util.MockInputReader
import org.junit.jupiter.api.Test

class InterpreterNewTest {
    private val interpreterManager = InterpreterManager(interpreters, KotlinPrinter(), KotlinEnvReader(), MockInputReader())

    @Test
    fun declared_variable_should_be_added_to_context() {
        val declarationNode = declaration
        val context = Context()
        val declarationInterpreter = interpreterManager.getInterpreter(declarationNode)
        val result = declarationInterpreter.interpret(declarationNode, context, interpreterManager)
        assert(result is InterpretResult.ContextResult)
        val newContext = (result as InterpretResult.ContextResult).context
        assert(newContext.isInContext("name"))
    }

    @Test
    fun `reassigning nonexistent value should throw error`() {
        val reassignationNode = input_001
        val context = Context()
        val interpreterManager = InterpreterManager(interpreters, KotlinPrinter(), KotlinEnvReader(), KotlinInputReader())
        val reassignationInterpreter = interpreterManager.getInterpreter(reassignationNode)
        try {
            reassignationInterpreter.interpret(reassignationNode, context, interpreterManager)
            assert(false)
        } catch (e: Exception) {
            assert(true)
        }
    }

    @Test
    fun `reassigning value should update context`() {
        val declarationResult =
            interpreterManager.getInterpreter(
                declaration,
            ).interpret(declaration, Context(), interpreterManager) as InterpretResult.ContextResult
        val context = declarationResult.context
        val reassignationNode = input_001
        val reassignationInterpreter = interpreterManager.getInterpreter(reassignationNode)
        val result = reassignationInterpreter.interpret(reassignationNode, context, interpreterManager)
        assert(result is InterpretResult.ContextResult)
        val newContext = (result as InterpretResult.ContextResult).context
        assert(newContext.getVariable("name") == "name")
    }

    @Test
    fun `printingNodes should print message passed `() {
        val context = Context()
        val printNode = input_002
        val printInterpreter = interpreterManager.getInterpreter(printNode)
        val result = printInterpreter.interpret(printNode, context, interpreterManager)
        assert(result is InterpretResult.ContextResult)
        val contextAfterPrint = (result as InterpretResult.ContextResult).context
        assert(contextAfterPrint.getPrintValues().contains("Hello world"))
    }

    @Test
    fun `declarationAssignation should create a constant`() {
        val declarationAssignationNode = input_003
        val context = Context()
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(declarationAssignationNode)
        val result = declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
        assert(result is InterpretResult.ContextResult)
        val newContext = (result as InterpretResult.ContextResult).context
        assert(newContext.isInContext("age"))
        assert(newContext.getConstant("age") == 20)
    }

    @Test
    fun `constant cannot be redeclared`() {
        val declarationAssignationNode = input_003
        val context = Context()
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(declarationAssignationNode)
        val result = declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
        assert(result is InterpretResult.ContextResult)
        val newContext = (result as InterpretResult.ContextResult).context
        val redeclarationNode = input_004
        val redeclarationInterpreter = interpreterManager.getInterpreter(redeclarationNode)
        try {
            redeclarationInterpreter.interpret(redeclarationNode, newContext, interpreterManager)
            assert(false)
        } catch (e: Exception) {
            assert(true)
        }
    }

    @Test
    fun ` should be able to reasign variable`() {
        val declarationAssignationNode = input_003let
        val context = Context()
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(declarationAssignationNode)
        val result = declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
        val newContext = (result as InterpretResult.ContextResult).context
        val reassignationNode = input_004
        val reassignationInterpreter = interpreterManager.getInterpreter(reassignationNode)
        val result2 = reassignationInterpreter.interpret(reassignationNode, newContext, interpreterManager)
        assert(result2 is InterpretResult.ContextResult)
        val newContext2 = (result2 as InterpretResult.ContextResult).context
        assert(newContext2.getVariable("age") == 30)
    }

    @Test
    fun `Sum operation should be sucessful`() {
        val context = Context()
        val sumNode = input_005
        val sumInterpreter = interpreterManager.getInterpreter(sumNode, NumberNodeType)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == 4)
    }

    @Test
    fun `Substraction operation should be sucessful`() {
        val context = Context()
        val sumNode = input_006
        val sumInterpreter = interpreterManager.getInterpreter(sumNode, NumberNodeType)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == 0)
    }

    @Test
    fun `Multiplication operation should be sucessful`() {
        val context = Context()
        val sumNode = input_007
        val sumInterpreter = interpreterManager.getInterpreter(sumNode, NumberNodeType)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == 4)
    }

    @Test
    fun `Division operation should be sucessful`() {
        val context = Context()
        val sumNode = input_008
        val sumInterpreter = interpreterManager.getInterpreter(sumNode, NumberNodeType)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == 1)
    }

    @Test
    fun `if changes to variables of the program scope should be reflected if it enters if block`() {
        val declarationAssignationNode = input_003let
        val context = Context()
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(declarationAssignationNode)
        val result = declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
        val newContext = (result as InterpretResult.ContextResult).context
        val ifNode = input_009
        val ifInterpreter = interpreterManager.getInterpreter(ifNode)
        val result2 = ifInterpreter.interpret(ifNode, newContext, interpreterManager)
        assert(result2 is InterpretResult.ContextResult)
        val newContext2 = (result2 as InterpretResult.ContextResult).context
        assert(newContext2.getVariable("age") == 30)
    }

    @Test
    fun `if changes to variables of the program scope should be reflected if it enters else block`() {
        val declarationAssignationNode = input_003let
        val context = Context()
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(declarationAssignationNode)
        val result = declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
        val newContext = (result as InterpretResult.ContextResult).context
        val ifNode = input_009else
        val ifInterpreter = interpreterManager.getInterpreter(ifNode)
        val result2 = ifInterpreter.interpret(ifNode, newContext, interpreterManager)
        assert(result2 is InterpretResult.ContextResult)
        val newContext2 = (result2 as InterpretResult.ContextResult).context
        assert(newContext2.getVariable("age") == 30)
    }

    @Test
    fun `input reader should work with mock input reader`() {
        val context = Context()
        val readInputNode = input_010
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(readInputNode)
        val result =
            declarationAssignationInterpreter.interpret(
                readInputNode,
                context,
                interpreterManager,
            ) as InterpretResult.ContextResult
        val newContext = result.context
        assert(newContext.getConstant("input") == "mocked input")
    }

    @Test
    fun `input reader should work with real input reader`() {
        val context = Context()
        val readInputNode = input_011
        val interpreterManager = InterpreterManager(interpreters, KotlinPrinter(), KotlinEnvReader(), KotlinInputReader())
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(readInputNode)
        val result =
            declarationAssignationInterpreter.interpret(
                readInputNode,
                context,
                interpreterManager,
            ) as InterpretResult.ContextResult
        val newContext = result.context
        assert(newContext.getConstant("input") == System.getenv("JAVA_HOME"))
    }
}
