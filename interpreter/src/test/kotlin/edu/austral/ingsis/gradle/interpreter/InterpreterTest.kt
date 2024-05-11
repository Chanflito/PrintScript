package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.interpreter.util.Context
import edu.austral.ingsis.gradle.interpreter.util.InterpretResult
import edu.austral.ingsis.gradle.interpreter.util.InterpreterList
import edu.austral.ingsis.gradle.interpreter.util.KotlinEnvReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinInputReader
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
        val printer = interpreterManager.printer as PrinterCollector
        assert(printer.getPrintedValues().contains("Hello world"))
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
        val newContext1 = cli_test2_part1(context)
        val newContext2 = cli_test2_part2(newContext1)
        val newContext3 = cli_test2_part3(newContext2)
        val result4 = cli_test2_part4(newContext3)

        assert_cli_test2_Part5(result4)
    }

    private fun cli_test2_part1(context: Context): Context {
        val node1 = input_cli1
        val interpreter1 = interpreterManager.getInterpreter(node1)
        val result1 = interpreter1.interpret(node1, context, interpreterManager)
        assert(result1 is InterpretResult.ContextResult)
        val value1 = (result1 as InterpretResult.ContextResult).context
        return context.update(value1)
    }

    private fun cli_test2_part2(context: Context): Context {
        val node2 = input_cli2
        val interpreter2 = interpreterManager.getInterpreter(node2)
        val result2 = interpreter2.interpret(node2, context, interpreterManager)
        assert(result2 is InterpretResult.ContextResult)
        val value2 = (result2 as InterpretResult.ContextResult).context
        return context.update(value2)
    }

    private fun cli_test2_part3(context: Context): Context {
        val node3 = input_cli3
        val interpreter3 = interpreterManager.getInterpreter(node3)
        val result3 = interpreter3.interpret(node3, context, interpreterManager)
        assert(result3 is InterpretResult.ContextResult)
        val value3 = (result3 as InterpretResult.ContextResult).context
        return context.update(value3)
    }

    private fun cli_test2_part4(context: Context): InterpretResult {
        val node4 = input_cli4
        val interpreter4 = interpreterManager.getInterpreter(node4)
        return interpreter4.interpret(node4, context, interpreterManager)
    }

    private fun assert_cli_test2_Part5(result: InterpretResult) {
        assert(result is InterpretResult.ContextResult)
        val expected = (5.0 / 9.0).toString()
        val printer = interpreterManager.printer as PrinterCollector
        assert(printer.getPrintedValues().contains(expected))
    }

    @Test
    fun `compose interpreter should interpret any input`() {
        val composeInterpreter =
            ComposeInterpreter(
                InterpreterList().getInterpreters(),
                PrinterCollector(),
                KotlinEnvReader(),
                KotlinInputReader(),
            )
        val composeInterpreter2 = composeInterpreter.interpretAndUpdateContext(input_cli1)
        val composeInterpreter3 = composeInterpreter2.interpretAndUpdateContext(input_cli2)
        assert(composeInterpreter3.getContext().getConstant("b") == 9)
        val composeInterpreter4 = composeInterpreter3.interpretAndUpdateContext(input_cli3)
        val composeInterpreter5 = composeInterpreter4.interpretAndUpdateContext(input_cli4)
        val expected = (5.0 / 9.0).toString()
        val printer = composeInterpreter5.getPrinter() as PrinterCollector
        assert(printer.getPrintedValues().contains(expected))
    }
}
