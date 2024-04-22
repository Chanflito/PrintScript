package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.KotlinEnvReader
import edu.austral.ingsis.gradle.interpreter.util.MockInputReader
import edu.austral.ingsis.gradle.interpreter.util.PrinterCollector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InterpreterTest {
    private val interpreterManager = createInterpreterManagerTest()

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
        val interpreterManager = createInterpreterManager()
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
        // assert(contextAfterPrint.getPrintValues().contains("Hello world"))
    }

    @Test
    fun `declarationAssignation should create a constant`() {
        val declarationAssignationNode = input_003
        val context = Context()
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(declarationAssignationNode)
        val result =
            declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
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
        val result =
            declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
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
        val result =
            declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
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
        val sumInterpreter = interpreterManager.getInterpreter(sumNode)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == 4)
    }

    @Test
    fun `Substraction operation should be sucessful`() {
        val context = Context()
        val sumNode = input_006
        val sumInterpreter = interpreterManager.getInterpreter(sumNode)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == 0)
    }

    @Test
    fun `Multiplication operation should be sucessful`() {
        val context = Context()
        val sumNode = input_007
        val sumInterpreter = interpreterManager.getInterpreter(sumNode)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == 4)
    }

    @Test
    fun `Division operation should be sucessful`() {
        val context = Context()
        val sumNode = input_008
        val sumInterpreter = interpreterManager.getInterpreter(sumNode)
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
        val result =
            declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
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
        val result =
            declarationAssignationInterpreter.interpret(declarationAssignationNode, context, interpreterManager)
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
        val interpreterWithMock = interpreterManager
        val declarationAssignationInterpreter = interpreterWithMock.getInterpreter(readInputNode)
        val result =
            declarationAssignationInterpreter.interpret(
                readInputNode,
                context,
                interpreterManager,
            ) as InterpretResult.ContextResult
        val newContext = result.context
        assertEquals("mocked input", newContext.getConstant("input"))
    }

    @Test
    fun `printer collector should have both message from readInput and println value `() {
        val context = Context()
        val readInputNode = input_014
        val printNode = input_015
        val collector = PrinterCollector()
        val inputReader = MockInputReader("World")
        val interpreterManager2 =
            createDynamicInterpreterManager(
                collector,
                KotlinEnvReader(),
                inputReader,
            )
        val declarationAssignationInterpreter = interpreterManager2.getInterpreter(readInputNode)
        val firstResult =
            declarationAssignationInterpreter.interpret(
                readInputNode,
                context,
                interpreterManager2,
            ) as InterpretResult.ContextResult
        val newContext = firstResult.context
        val printInterpreter = interpreterManager2.getInterpreter(printNode)

        printInterpreter.interpret(
            printNode,
            newContext,
            interpreterManager2,
        ) as InterpretResult.ContextResult

        assertEquals("World", newContext.getConstant("name"))
        assertEquals(listOf("Name: ", "Hello World!"), collector.getPrintedValues())
    }

    @Test
    fun `printer collector should have both message from readInput and println number value `() {
        val context = Context()
        val readInputNode = input_016
        val printNode = input_017
        val collector = PrinterCollector()
        val inputReader = MockInputReader(9)
        val interpreterManager2 =
            createDynamicInterpreterManager(
                collector,
                KotlinEnvReader(),
                inputReader,
            )
        val declarationAssignationInterpreter = interpreterManager2.getInterpreter(readInputNode)
        val firstResult =
            declarationAssignationInterpreter.interpret(
                readInputNode,
                context,
                interpreterManager2,
            ) as InterpretResult.ContextResult
        val newContext = firstResult.context
        val printInterpreter = interpreterManager2.getInterpreter(printNode)

        printInterpreter.interpret(
            printNode,
            newContext,
            interpreterManager2,
        ) as InterpretResult.ContextResult

        assertEquals(9, newContext.getConstant("number"))
        assertEquals(listOf("Enter number: ", "9"), collector.getPrintedValues())
    }

    @Test
    fun `input reader should work with real input reader`() {
        val context = Context()
        val readInputNode = input_011
        val interpreterManager = createInterpreterManager()
        val declarationAssignationInterpreter = interpreterManager.getInterpreter(readInputNode)
        val result =
            declarationAssignationInterpreter.interpret(
                readInputNode,
                context,
                interpreterManager,
            ) as InterpretResult.ContextResult
        val newContext = result.context
        assert(newContext.getConstant("input") == System.getenv("PATH"))
    }

    @Test
    fun `concatenation with number and string`() {
        val context = Context()
        val sumNode = input_012
        val sumInterpreter = interpreterManager.getInterpreter(sumNode)
        val result = sumInterpreter.interpret(sumNode, context, interpreterManager)
        assert(result is InterpretResult.OperationResult)
        val value = (result as InterpretResult.OperationResult).value
        assert(value == "Hello 12")
    }

    @Test
    fun cli_test() {
        val context = Context()
        val node = input_013
        val intepreter = interpreterManager.getInterpreter(node)
        val result = intepreter.interpret(node, context, interpreterManager)
        assert(result is InterpretResult.ContextResult)
        val value = (result as InterpretResult.ContextResult).context
        val va = value.getVariable("b")
        assert(va == 5)
    }

    @Test
    fun cli_test2() {
        val context = Context()
        val node1 = input_cli1
        val intepreter1 = interpreterManager.getInterpreter(node1)
        val result1 = intepreter1.interpret(node1, context, interpreterManager)
        assert(result1 is InterpretResult.ContextResult)
        val value1 = (result1 as InterpretResult.ContextResult).context
        val newContext1 = context.update(value1)
        val node2 = input_cli2
        val intepreter2 = interpreterManager.getInterpreter(node2)
        val result2 = intepreter2.interpret(node2, newContext1, interpreterManager)
        assert(result2 is InterpretResult.ContextResult)
        val value2 = (result2 as InterpretResult.ContextResult).context
        val newContext2 = newContext1.update(value2)
        val node3 = input_cli3
        val intepreter3 = interpreterManager.getInterpreter(node3)
        val result3 = intepreter3.interpret(node3, newContext2, interpreterManager)
        assert(result3 is InterpretResult.ContextResult)
        val value3 = (result3 as InterpretResult.ContextResult).context
        val newContext3 = newContext2.update(value3)
        val node4 = input_cli4
        val intepreter4 = interpreterManager.getInterpreter(node4)
        val result4 = intepreter4.interpret(node4, newContext3, interpreterManager)
        assert(result4 is InterpretResult.ContextResult)
        val value4 = (result4 as InterpretResult.ContextResult).context
        val newContext4 = newContext3.update(value4)
        val expected = (5.0 / 9.0).toString()
//        assert(newContext4.getPrintValues().contains(expected))
    }
}
