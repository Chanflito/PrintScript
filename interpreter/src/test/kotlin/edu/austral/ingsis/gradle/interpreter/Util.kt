package edu.austral.ingsis.gradle.interpreter

import edu.austral.ingsis.gradle.common.ast.newast.BooleanNodeType
import edu.austral.ingsis.gradle.common.ast.newast.NumberNodeType
import edu.austral.ingsis.gradle.common.ast.newast.StringNodeType
import edu.austral.ingsis.gradle.interpreter.util.EnvReader
import edu.austral.ingsis.gradle.interpreter.util.InputReader
import edu.austral.ingsis.gradle.interpreter.util.InterpreterManager
import edu.austral.ingsis.gradle.interpreter.util.KotlinEnvReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinInputReader
import edu.austral.ingsis.gradle.interpreter.util.KotlinPrinter
import edu.austral.ingsis.gradle.interpreter.util.MockInputReader
import edu.austral.ingsis.gradle.interpreter.util.Printer

fun createInterpreterManager(): InterpreterManager {
    val interpreters =
        listOf(
            BlockInterpreter(),
            DeclarationInterpreter(),
            StringLiteralInterpreter(),
            NumberLiteralInterpreter(),
            BooleanLiteralInterpreter(),
            SumInterpreter(),
            SubtractInterpreter(),
            MultiplyInterpreter(),
            DivideInterpreter(),
            DeclarationInterpreter(),
            DeclarationAssignationInterpreter(),
            IdentifierInterpreter(),
            IfElseStatementInterpreter(),
            IfStatementInterpreter(),
            PrintLnInterpreter(),
            ReadEnvInterpreter(StringNodeType),
            ReadEnvInterpreter(NumberNodeType),
            ReadEnvInterpreter(BooleanNodeType),
            ReadInputInterpreter(StringNodeType),
            ReadInputInterpreter(NumberNodeType),
            ReadInputInterpreter(BooleanNodeType),
            ReAssignationInterpreter(),
        )

    val printer = KotlinPrinter()
    val reader = KotlinEnvReader()
    val inputReader = KotlinInputReader()

    return InterpreterManager(interpreters, printer, reader, inputReader)
}

fun createInterpreterManagerTest(): InterpreterManager {
    val interpreters =
        listOf(
            BlockInterpreter(),
            DeclarationInterpreter(),
            StringLiteralInterpreter(),
            NumberLiteralInterpreter(),
            BooleanLiteralInterpreter(),
            SumInterpreter(),
            SubtractInterpreter(),
            MultiplyInterpreter(),
            DivideInterpreter(),
            DeclarationInterpreter(),
            DeclarationAssignationInterpreter(),
            IdentifierInterpreter(),
            IfElseStatementInterpreter(),
            IfStatementInterpreter(),
            PrintLnInterpreter(),
            ReadEnvInterpreter(StringNodeType),
            ReadEnvInterpreter(NumberNodeType),
            ReadEnvInterpreter(BooleanNodeType),
            ReadInputInterpreter(StringNodeType),
            ReadInputInterpreter(NumberNodeType),
            ReadInputInterpreter(BooleanNodeType),
            ReAssignationInterpreter(),
        )

    val printer = KotlinPrinter()
    val reader = KotlinEnvReader()
    val inputReader = MockInputReader("mocked input")

    return InterpreterManager(interpreters, printer, reader, inputReader)
}

fun createDynamicInterpreterManager(
    printer: Printer,
    envReader: EnvReader,
    inputReader: InputReader,
): InterpreterManager {
    val interpreters =
        listOf(
            BlockInterpreter(),
            DeclarationInterpreter(),
            StringLiteralInterpreter(),
            NumberLiteralInterpreter(),
            BooleanLiteralInterpreter(),
            SumInterpreter(),
            SubtractInterpreter(),
            MultiplyInterpreter(),
            DivideInterpreter(),
            DeclarationInterpreter(),
            DeclarationAssignationInterpreter(),
            IdentifierInterpreter(),
            IfElseStatementInterpreter(),
            IfStatementInterpreter(),
            PrintLnInterpreter(),
            ReadEnvInterpreter(StringNodeType),
            ReadEnvInterpreter(NumberNodeType),
            ReadEnvInterpreter(BooleanNodeType),
            ReadInputInterpreter(StringNodeType),
            ReadInputInterpreter(NumberNodeType),
            ReadInputInterpreter(BooleanNodeType),
            ReAssignationInterpreter(),
        )

    return InterpreterManager(interpreters, printer, envReader, inputReader)
}
